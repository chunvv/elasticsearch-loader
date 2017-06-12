package com.chariot.shadow.supplier.factory;

import com.chariot.shadow.supplier.*;

/**
 * Factory for Supplier class
 * <p>
 * Created by Trung Vu on 2017/05/27.
 */
public class SupplierFactory {

    public static Supplier create(SupplierType supplierType) {
        return new Supplier(
                new SupplierID(supplierType.getId()),
                new SupplierCode(supplierType.getCode()),
                new SupplierName(supplierType.getName())
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
