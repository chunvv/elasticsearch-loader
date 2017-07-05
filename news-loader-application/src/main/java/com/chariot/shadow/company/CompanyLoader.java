package com.chariot.shadow.company;

import com.chariot.shadow.Loader;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.Item;
import com.chariot.shadow.supplier.SupplierID;
import com.chariot.shadow.supplier.company.CompanySupplierType;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Created by Trung Vu on 2017/07/06.
 */
public class CompanyLoader extends Loader {

    private CompanyRepository companyRepository = new CompanyRepository();

    @Override
    public void init() {
        indexName = "company";
        super.init();
    }

    @Override
    public LinkedList<Future<Index>> submit() {
        LinkedList<Future<Index>> result = new LinkedList<>();
        result.addAll(submitIndexCompanies());
        result.addAll(submitDeleteCompanies());

        return result;
    }

    private LinkedList<Future<Index>> submitIndexCompanies() {
        return companyRepository.retrieveInsertCompanies().
                stream().
                map(company -> executor.
                        submit(new CompanyIndexTaskLoader(queue, new Id(company.getId().getId())))).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private LinkedList<Future<Index>> submitDeleteCompanies() {
        return companyRepository.retrieveDeleteCompanies().
                stream().
                map(company -> executor.
                        submit(new CompanyDeleteTaskLoader(queue, new Id(company.getId().getId())))).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void updateUpdateSign(List<Item> items) {
        items.forEach(item -> companyRepository.updateUpdateSign(new CompanyID(item.id()), new SupplierID(CompanySupplierType.VIETNAM.getId()), 0));
    }
}
