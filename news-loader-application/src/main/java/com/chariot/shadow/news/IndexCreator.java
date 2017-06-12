package com.chariot.shadow.news;

import com.chariot.shadow.item.DeleteItem;
import com.chariot.shadow.item.IndexItem;
import lombok.Value;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;

/**
 * Created by Trung Vu on 2017/06/12.
 */
@Value
public class IndexCreator {

    public IndexRequestBuilder createIndex(Client client, String indexName, IndexItem item) {
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

    public DeleteRequestBuilder createDelete(Client client, String indexName, DeleteItem item) {
        return client.prepareDelete(indexName, item.getIndexTypeAsString(), item.id());
    }
}
