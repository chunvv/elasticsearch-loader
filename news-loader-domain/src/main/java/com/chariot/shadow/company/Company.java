package com.chariot.shadow.company;

import com.chariot.shadow.company.name.CompanyName;
import com.chariot.shadow.country.Country;
import com.chariot.shadow.supplier.Supplier;
import lombok.Value;

/**
 * Created by Trung Vu on 2017/07/04.
 */
@Value
public class Company {

    private CompanyID id;
    private CompanyName name;
    private CompanyAddress address;
    private Country country;
    private CompanyTelephone telephone;
    private CompanyWebUrl webUrl;
    private CompanyDescription description;
    private Supplier supplier;

    public int getSupplierId() {
        return supplier.getIdAsInt();
    }

    public String getSupplierCode() {
        return supplier.getCodeAsString();
    }

    public String getSupplierName() {
        return supplier.getNameAsString();
    }
}
