package com.lucbir.search;

import com.lucbir.features.*;
import com.lucbir.search.LucbirSearcher;

/**
 * Created by Administrator on 2016/7/25.
 */
public class SearcherFactory {
    /**
     * 创建 {@link com.lucbir.features.AnnularColorLayoutHistogram} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     **/
    public static LucbirSearcher createACLHSearcher(){
        return new LucbirSearcher(new AnnularColorLayoutHistogram());
    }

    /**
     * 创建 {@link com.lucbir.features.ColorCoherenceVector} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     */
    public static LucbirSearcher createCCVSearcher(){
        return new LucbirSearcher(new ColorCoherenceVector());
    }

    /**
     * 创建 {@link com.lucbir.features.HSVColorHistogram} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     */
    public static LucbirSearcher createHSVHSearcher(){
        return new LucbirSearcher(new HSVColorHistogram());
    }

    /**
     * 创建 {@link com.lucbir.features.PHash} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     */
    public static LucbirSearcher createPHashSearcher(){
        return new LucbirSearcher(new PHash());
    }

    /**
     * 创建 {@link com.lucbir.features.RGBColorHistogram} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     */
    public static LucbirSearcher createRGBHSearcher(){
        return new LucbirSearcher(new RGBColorHistogram());
    }

    /**
     * 创建 {@link com.lucbir.features.RHash} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     */
    public static LucbirSearcher createRHashSearcher(){
        return new LucbirSearcher(new RHash());
    }

    /**
     * 创建 {@link com.lucbir.features.UniformLBP} 的搜索器
     * @return {@link com.lucbir.search.LucbirSearcher}
     */
    public static LucbirSearcher createULBPSearcher(){
        return new LucbirSearcher(new UniformLBP());
    }
}
