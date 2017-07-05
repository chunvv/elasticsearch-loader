package com.chariot.shadow.company.name;

import lombok.Value;

/**
 * Created by Trung Vu on 2017/07/04.
 */
@Value
public class CompanyName {

    private CompanyEnglishName englishName;
    private CompanyLocalName localName;

    public String getCompanyEnglishNameAsString() {
        return englishName.getName();
    }

    public String getCompanyLocalNameAsString() {
        return localName.getName();
    }
}
