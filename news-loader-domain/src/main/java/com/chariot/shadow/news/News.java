package com.chariot.shadow.news;

import com.chariot.shadow.supplier.Supplier;
import lombok.Value;

import java.util.Date;

/**
 * News contains some information as id, title, content, link, published data and the supplier is belong to
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
@Value
public class News {

    private NewsID id;
    private Title title;
    private Content content;
    private Link link;
    private PublicationDate publicationDate;

    private Supplier supplier;

    public String getIdAsString() {
        return id.getNewsID();
    }

    public String getTitleAsString() {
        return title.getTitle();
    }

    public String getContentAsString() {
        return content.getContent();
    }

    public String getLinkAsString() {
        return String.valueOf(link.getLink());
    }

    public Date getPublicationDateAsDate() {
        return publicationDate.getDate();
    }

    public String getSupplierIdAsString() {
        return String.valueOf(supplier.getIdAsInt());
    }
}
