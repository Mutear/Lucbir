package com.lucbir.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Created by VenyoWang on 2016/7/8.
 */
public class ImageUtil {
    public static Pixel[][] getImagePixel(BufferedImage srcImg, int width, int height) {
    	Pixel[][] colors = shrinkBitmap(srcImg, width, height);
        int minx = 0;
        int miny = 0;
        Pixel[][] rgbMatrix = new Pixel[width - minx][height - miny];
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
            	Pixel pixel = colors[i][j];
                rgbMatrix[i - minx][j - miny] = pixel;
            }
        }
        return rgbMatrix;
    }

    public static int[][] getGrayPixel(BufferedImage srcImg, int width, int height) {
    	Pixel[][] colors = shrinkBitmap(srcImg, width, height);
        int minx = 0;
        int miny = 0;
        int[][] matrix = new int[width - minx][height - miny];
        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
            	Pixel pixel = colors[i][j];
                int gray = (int) (pixel.red * 0.3 + pixel.green * 0.59 + pixel.blue * 0.11);
                matrix[i][j] = gray;
            }
        }
        return matrix;
    }
    
    public static Pixel[][] shrinkBitmap(BufferedImage srcImg, int width, int height)
    {
        if (srcImg != null)
        {
            int imgHeight = srcImg.getHeight();
            int imgWidth = srcImg.getWidth();
            if (imgHeight < height || imgWidth < width)
            {
                return null;
            }
            else
            {
            	Pixel[][] colors = new Pixel[height][width];
                double u = (double)imgHeight / height;
                double v = (double)imgWidth / width;
                for (int i = 0; i < height; i++)
                {
                    for (int j = 0; j < width; j++)
                    {
                        double x = u * i;
                        double y = v * j;
                        int x1 = (int)x, y1 = (int)y, x2 = (int)(x + u), y2 = (int)(y + v);
                        if (x2 >= imgHeight)
                        {
                            x2 = imgHeight - 1;
                        }
                        if (y2 >= imgWidth)
                        {
                            y2 = imgWidth - 1;
                        }
                        int c1 = srcImg.getRGB(y1, x1), c2 = srcImg.getRGB(y2, x1), c3 = srcImg.getRGB(y1, x2), c4 = srcImg.getRGB(y2, x2);
                        Pixel pixel = new Pixel();
                        pixel.red = (((c1 & 0xff0000) >> 16) + ((c2 & 0xff0000) >> 16) + ((c3 & 0xff0000) >> 16) + ((c4 & 0xff0000) >> 16)) / 4;
                        pixel.green = (((c1 & 0xff00) >> 8) + ((c2 & 0xff00) >> 8) + ((c3 & 0xff00) >> 8) + ((c4 & 0xff00) >> 8)) / 4;
                        pixel.blue = ((c1 & 0xff) + (c2 & 0xff) + (c3 & 0xff) + (c4 & 0xff)) / 4;
                        colors[i][j] = pixel;
                    }
                }
                return colors;
            }
        }
        return null;
    }

    public static double calculateSimilarity(int[][] matrix1, int[][] matrix2) {
        return calculateSimilarity(Util.matrix2vector(matrix1), Util.matrix2vector(matrix2));
    }

    public static double calculateSimilarity(int[] vector1, int[] vector2) {
        if(vector1 == null || vector2 == null){
            throw new NullPointerException();
        }
        if(vector1.length != vector2.length){
            throw new RuntimeException("两向量长度不相等");
        }
        double len1 = 0, len2 = 0, numerator = 0;
        for (int i = 0; i < vector1.length; i++) {
            len1 += Math.pow(vector1[i], 2);
            len2 += Math.pow(vector2[i], 2);
            numerator += vector1[i] * vector2[i];
        }
        len1 = Math.sqrt(len1);
        len2 = Math.sqrt(len2);

        return numerator / (len1 * len2);
    }

    /**
     * 用于计算pHash的相似度<br>
     * 相似度为1时，图片最相似
     * @param str1
     * @param str2
     * @return
     */
    public static double calculateSimilarity(String str1, String str2) {
        if(Util.isStringEmpty(str1) || Util.isStringEmpty(str2)){
            throw new NullPointerException();
        }
        int num = 0;
        for(int i = 0; i < 64; i++){
            if(str1.charAt(i) == str2.charAt(i)){
                num++;
            }
        }
        return ((double)num) / 64.0;
    }

    /**
     * 离散余弦变换
     * @author luoweifu
     *
     * @param pix
     *            原图像的数据矩阵
     * @param n
     *            原图像(n*n)的高或宽
     * @return 变换后的矩阵数组
     */
    public static int[][] DCT(int[][] pix, int n) {
        double[][] iMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                iMatrix[i][j] = (double) (pix[i][j]);
            }
        }
        double[][] quotient = coefficient(n); // 求系数矩阵
        double[][] quotientT = transposingMatrix(quotient, n); // 转置系数矩阵

        double[][] temp = new double[n][n];
        temp = matrixMultiply(quotient, iMatrix, n);
        iMatrix = matrixMultiply(temp, quotientT, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pix[i][j] = (int) (iMatrix[i][j]);
            }
        }
        return pix;
    }

    /**
     * 求离散余弦变换的系数矩阵
     * @author luoweifu
     *
     * @param n
     *            n*n矩阵的大小
     * @return 系数矩阵
     */
    private static double[][] coefficient(int n) {
        double[][] coeff = new double[n][n];
        double sqrt = 1.0 / Math.sqrt(n);
        for (int i = 0; i < n; i++) {
            coeff[0][i] = sqrt;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coeff[i][j] = Math.sqrt(2.0 / n) * Math.cos(i * Math.PI * (j + 0.5) / (double) n);
            }
        }
        return coeff;
    }

    /**
     * 矩阵转置
     * @author luoweifu
     *
     * @param matrix
     *            原矩阵
     * @param n
     *            矩阵(n*n)的高或宽
     * @return 转置后的矩阵
     */
    private static double[][] transposingMatrix(double[][] matrix, int n) {
        double nMatrix[][] = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nMatrix[i][j] = matrix[j][i];
            }
        }
        return nMatrix;
    }

    /**
     * 矩阵相乘
     * @author luoweifu
     *
     * @param A
     *            矩阵A
     * @param B
     *            矩阵B
     * @param n
     *            矩阵的大小n*n
     * @return 结果矩阵
     */
    private static double[][] matrixMultiply(double[][] A, double[][] B, int n) {
        double nMatrix[][] = new double[n][n];
        int t = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                t = 0;
                for (int k = 0; k < n; k++) {
                    t += A[i][k] * B[k][j];
                }
                nMatrix[i][j] = t;
            }
        }
        return nMatrix;
    }
}
