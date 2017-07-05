package com.chariot.shadow.news.util;

import com.chariot.shadow.news.News;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public class NewsMapBuilder {

    public static Map<String, Object> build(News news) {
        Map<String, Object> result = new HashMap<>();

        result.put("newsId", news.getIdAsString());
        result.put("supplierId", news.getSupplierIdAsString());
        result.put("title", news.getTitleAsString());
        result.put("content", news.getContentAsString());
        result.put("link", news.getLinkAsString());
        result.put("publishDate", news.getPublicationDateAsDate());
        result.put("language", news.getLanguage().getCode());
        result.put("country", news.getCountry().getIsoCode());
        
        return result;
    }
}
