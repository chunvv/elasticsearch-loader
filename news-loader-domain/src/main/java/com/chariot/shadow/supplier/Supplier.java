package com.chariot.shadow.supplier;

import lombok.Value;

/**
 * Supplier contains some information as id, supplier code and supplier name
 * <p>
 * Created by Trung Vu on 2017/05/23.
 */
@Value
public class Supplier {

    private SupplierID id;
    private SupplierCode code;
    private SupplierName name;

    public int getIdAsInt() {
        return id.getId();
    }

    public String getCodeAsString() {
        return code.getCode();
    }

    public String getNameAsString() {
        return name.getName();
    }
}
