package com.chariot.shadow.news.util;

import com.chariot.shadow.news.*;
import com.chariot.shadow.news.factory.NewsFactory;
import com.chariot.shadow.supplier.SupplierType;
import com.chariot.shadow.supplier.factory.SupplierFactory;
import lombok.Value;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;

/**
 * Mapping ArticleEntry to News object
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
@Value
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
                    new Link(new URL(newsEntity.getLink())),
                    new PublicationDate(newsEntity.getPublishDate()),
                    SupplierFactory.create(
                            SupplierType.get(
                                    Integer.valueOf(newsEntity.getSupplierId())))
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
