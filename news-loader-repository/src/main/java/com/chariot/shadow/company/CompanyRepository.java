package com.chariot.shadow.company;

import com.chariot.shadow.supplier.SupplierID;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Trung Vu on 2017/07/04.
 */
public class CompanyRepository {

    private CompanyInfrastructure companyInfrastructure = new CompanyInfrastructure();

    public List<Company> retrieveInsertCompanies() {
        return companyInfrastructure.retrieveInsertCompanies().stream().map(CompanyMapper::map).collect(Collectors.toList());
    }

    public List<Company> retrieveDeleteCompanies() {
        return companyInfrastructure.retrieveDeleteCompanies().stream().map(CompanyMapper::map).collect(Collectors.toList());
    }

    public void updateUpdateSign(CompanyID companyID, SupplierID supplierID, int updateSign) {
        companyInfrastructure.updateUpdateSign(companyID.getId(), String.valueOf(supplierID.getId()), updateSign);
    }
}