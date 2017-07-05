package com.chariot.shadow.news.factory;

import com.chariot.shadow.country.Country;
import com.chariot.shadow.language.Language;
import com.chariot.shadow.news.*;
import com.chariot.shadow.supplier.Supplier;

/**
 * Creating new instance of News
 * <p>
 * Created by Trung Vu on 2017/05/24.
 */
public class NewsFactory {

    public static News create(NewsID id, Title title, Content content, Link link, PublicationDate publicationDate, Language language, Country country, Supplier supplier) {
        return new News(id, title, content, link, publicationDate, language, country, supplier);
    }
}