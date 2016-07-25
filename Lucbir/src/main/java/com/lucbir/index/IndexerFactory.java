package com.lucbir.index;

import com.lucbir.features.*;

/**
 * Created by Administrator on 2016/7/25.
 */
public class IndexerFactory {
    /**
     * 创建 {@link com.lucbir.features.AnnularColorLayoutHistogram} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     **/
    public static LucbirIndexer createACLHIndexer(){
        return new LucbirIndexer(new AnnularColorLayoutHistogram());
    }

    /**
     * 创建 {@link com.lucbir.features.ColorCoherenceVector} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     */
    public static LucbirIndexer createCCVIndexer(){
        return new LucbirIndexer(new ColorCoherenceVector());
    }

    /**
     * 创建 {@link com.lucbir.features.HSVColorHistogram} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     */
    public static LucbirIndexer createHSVHIndexer(){
        return new LucbirIndexer(new HSVColorHistogram());
    }

    /**
     * 创建 {@link com.lucbir.features.PHash} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     */
    public static LucbirIndexer createPHashIndexer(){
        return new LucbirIndexer(new PHash());
    }

    /**
     * 创建 {@link com.lucbir.features.RGBColorHistogram} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     */
    public static LucbirIndexer createRGBHIndexer(){
        return new LucbirIndexer(new RGBColorHistogram());
    }

    /**
     * 创建 {@link com.lucbir.features.RHash} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     */
    public static LucbirIndexer createRHashIndexer(){
        return new LucbirIndexer(new RHash());
    }

    /**
     * 创建 {@link com.lucbir.features.UniformLBP} 的索引器
     * @return {@link com.lucbir.index.LucbirIndexer}
     */
    public static LucbirIndexer createULBPIndexer(){
        return new LucbirIndexer(new UniformLBP());
    }
}
