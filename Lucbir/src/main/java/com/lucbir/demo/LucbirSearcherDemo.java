package com.lucbir.demo;

import com.lucbir.features.PHash;
import com.lucbir.search.LucbirSearcher;
import com.lucbir.utils.LucbirResult;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class LucbirSearcherDemo {
    public static void main(String[] args){
        LucbirSearcher searcher = new LucbirSearcher(new PHash());
        List<LucbirResult> resultList = searcher.searchWithListResult(new File("D:\\Documents\\pics\\005BKX9Jjw1eiinda3e2uj309j0790t8_.jpg"), 3, "D:\\Documents\\index");
        for(int i = 0; i < resultList.size(); i++){
            System.out.println(resultList.get(i).getImagePath() + " " + resultList.get(i).getSimilarity());
        }
    }
}
