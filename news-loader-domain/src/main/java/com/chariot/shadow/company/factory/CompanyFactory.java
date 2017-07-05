package com.chariot.shadow.company.factory;

import com.chariot.shadow.company.*;
import com.chariot.shadow.company.name.CompanyName;
import com.chariot.shadow.country.Country;
import com.chariot.shadow.supplier.Supplier;

/**
 * Created by Trung Vu on 2017/07/05.
 */
public class CompanyFactory {

    public static Company create(CompanyID id, CompanyName name, CompanyAddress address, Country country, CompanyTelephone telephone, CompanyWebUrl webUrl, CompanyDescription description, Supplier supplier) {
        return new Company(id, name, address, country, telephone, webUrl, description, supplier);
    }
}
