package com.chariot.shadow.company;

import com.chariot.shadow.company.factory.CompanyFactory;
import com.chariot.shadow.company.name.CompanyEnglishName;
import com.chariot.shadow.company.name.CompanyLocalName;
import com.chariot.shadow.company.name.CompanyName;
import com.chariot.shadow.country.Country;
import com.chariot.shadow.economy.company.CompanyEntity;
import com.chariot.shadow.supplier.Supplier;
import com.chariot.shadow.supplier.SupplierCode;
import com.chariot.shadow.supplier.SupplierID;
import com.chariot.shadow.supplier.SupplierName;
import com.chariot.shadow.supplier.company.CompanySupplierType;

/**
 * Created by Trung Vu on 2017/07/04.
 */
public class CompanyMapper {

    public static Company map(CompanyEntity entity) {
        CompanySupplierType supplierType = CompanySupplierType.get(Integer.valueOf(entity.getSupplierId()));
        return CompanyFactory.create(
                new CompanyID(entity.getCompanyId()),
                new CompanyName(
                        new CompanyEnglishName(entity.getCompanyNameEnglish()),
                        new CompanyLocalName(entity.getCompanyNameLocal())
                ),
                new CompanyAddress(entity.getAddressRegistered()),
                Country.get(entity.getAddressCountry()),
                new CompanyTelephone(entity.getTelNumber()),
                new CompanyWebUrl(entity.getWebUrl()),
                new CompanyDescription(entity.getShortDescription()),
                new Supplier(
                        new SupplierID(supplierType.getId()),
                        new SupplierCode(supplierType.getCode()),
                        new SupplierName(supplierType.getName())
                )
        );
    }
}
