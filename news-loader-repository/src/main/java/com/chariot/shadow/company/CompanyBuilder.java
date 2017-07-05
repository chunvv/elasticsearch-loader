package com.chariot.shadow.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trung Vu on 2017/07/05.
 */
public class CompanyBuilder {

    public static Map<String, Object> build(Company company) {
        Map<String, Object> result = new HashMap<>();
        result.put("company_id", company.getId().getId());
        result.put("supplier_id", company.getSupplierId());
        result.put("company_name_english", company.getName().getCompanyEnglishNameAsString());
        result.put("company_local_name", company.getName().getCompanyLocalNameAsString());
        result.put("address", company.getAddress().getAddress());
        result.put("country", company.getCountry().getIsoCode());
        result.put("telephone", company.getTelephone().getTel());
        result.put("web_url", company.getWebUrl().getWebUrl());
        result.put("description", company.getDescription().getDescription());
        result.put("supplier_id", company.getSupplierId());
        return result;
    }
}
