package com.chariot.shadow.news;

import com.chariot.shadow.item.DeleteItem;
import com.chariot.shadow.item.IndexItem;
import com.chariot.shadow.item.Item;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;

import java.util.List;

/**
 * Created by Trung Vu on 2017/06/12.
 */
public class IndexCreator {

    public static IndexRequestBuilder createIndex(Client client, String indexName, IndexItem item) {
        IndexRequestBuilder index =
                client.prepareIndex(
                        indexName,
                        item.getIndexTypeAsString(),
                        item.id()).
                        setOpType(DocWriteRequest.OpType.INDEX).
                        setSource(item.getSource());

        if (index != null) {
            index.setParent(item.getParentAsString());
        }

        return index;
    }

    public static DeleteRequestBuilder createDelete(Client client, String indexName, DeleteItem item) {
        return client.prepareDelete(indexName, item.getIndexTypeAsString(), item.id());
    }

    public static BulkRequestBuilder createBulkIndex(Client client, List<Item> items, String indexName) {
        BulkRequestBuilder bulkIndex = client.prepareBulk();
        items.forEach(item -> {
            if (item instanceof IndexItem) {
                bulkIndex.add(createIndex(client, indexName, (IndexItem) item));
            } else {
                if (item instanceof DeleteItem) {
                    bulkIndex.add(createDelete(client, indexName, (DeleteItem) item));
                } else throw new UnsupportedOperationException();
            }
        });

        return bulkIndex;
    }
}
