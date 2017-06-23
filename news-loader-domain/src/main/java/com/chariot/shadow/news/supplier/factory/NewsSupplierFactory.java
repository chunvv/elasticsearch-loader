package com.chariot.shadow.news.supplier.factory;

import com.chariot.shadow.news.supplier.*;

/**
 * Factory for Supplier class
 * <p>
 * Created by Trung Vu on 2017/05/27.
 */
public class NewsSupplierFactory {

    public static Supplier create(NewsSupplierType newsSupplierType) {
        return new Supplier(
                new SupplierID(newsSupplierType.getId()),
                new SupplierCode(newsSupplierType.getCode()),
                new SupplierName(newsSupplierType.getName())
        );
    }

    public static Supplier create(SupplierID id, SupplierCode code, SupplierName name) {
        return new Supplier(id, code, name);
    }

    public static Supplier create(int id, String code, String name) {
        return new Supplier(
                new SupplierID(id),
                new SupplierCode(code),
                new SupplierName(name)
        );
    }
}
