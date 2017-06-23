package com.chariot.shadow.news.util;

import com.chariot.shadow.news.*;
import com.chariot.shadow.news.factory.NewsFactory;
import com.chariot.shadow.news.supplier.NewsSupplierType;
import com.chariot.shadow.news.supplier.factory.NewsSupplierFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

/**
 * Mapping ArticleEntry to News object
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsMapper {

    public static NewsEntity map(News news) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setNewsId(news.getIdAsString());
        newsEntity.setSupplierId(news.getSupplierIdAsString());
        newsEntity.setTitle(news.getTitleAsString());
        newsEntity.setContent(news.getContentAsString());
        newsEntity.setLink(news.getLinkAsString());
        newsEntity.setPublishDate(news.getPublicationDateAsDate());
        newsEntity.setRegistrationTimestamp(new Timestamp(System.currentTimeMillis()));
        return newsEntity;
    }

    public static News map(NewsEntity newsEntity) {
        try {
            return NewsFactory.create(
                    new NewsID(newsEntity.getNewsId()),
                    new Title(newsEntity.getTitle()),
                    new Content(newsEntity.getContent()),
                    newsEntity.getLink() == null ? null : new Link(new URL(newsEntity.getLink())),
                    new PublicationDate(newsEntity.getPublishDate()),
                    NewsSupplierFactory.create(
                            NewsSupplierType.get(
                                    Integer.valueOf(newsEntity.getSupplierId())))
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
