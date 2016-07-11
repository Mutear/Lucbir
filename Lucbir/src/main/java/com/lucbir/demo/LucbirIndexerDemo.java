package com.lucbir.demo;

import com.lucbir.features.PHash;
import com.lucbir.index.LucbirIndexer;

/**
 * Created by Administrator on 2016/7/11.
 */
public class LucbirIndexerDemo {
    public static void main(String[] args){
        LucbirIndexer indexer = new LucbirIndexer(new PHash());
        indexer.generateIndex("D:\\Documents\\index", "D:\\Documents\\pics");
    }
}
