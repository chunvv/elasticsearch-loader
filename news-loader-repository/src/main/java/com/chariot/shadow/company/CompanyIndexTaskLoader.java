package com.chariot.shadow.company;

import com.chariot.shadow.TaskLoader;
import com.chariot.shadow.executor.ShadowQueue;
import com.chariot.shadow.id.Id;
import com.chariot.shadow.indexing.Index;
import com.chariot.shadow.item.IndexItem;
import com.chariot.shadow.item.IndexType;
import com.chariot.shadow.supplier.company.CompanySupplierType;

/**
 * Created by Trung Vu on 2017/07/06.
 */
public class CompanyIndexTaskLoader extends TaskLoader<Id> {

    private CompanyInfrastructure companyInfrastructure = new CompanyInfrastructure();

    public CompanyIndexTaskLoader(ShadowQueue queue, Id id) {
        super(queue, id);
    }

    @Override
    public Index load() {
        Index index = new Index();
        index.add(
                new IndexItem(
                        IndexType.IMPORT,
                        getId(),
                        CompanyBuilder.build(
                                CompanyMapper.map(companyInfrastructure.find(getId().getId(), String.valueOf(CompanySupplierType.VIETNAM.getId()))))));
        return index;
    }
}
