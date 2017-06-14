package com.chariot.shadow.executor;

import com.chariot.shadow.Loader;
import com.chariot.shadow.item.Item;
import com.chariot.shadow.news.IndexCreator;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trung Vu on 2017/06/14.
 */
@Value
public class ElasticsearchThread implements Runnable, Notifier<Loader, Message> {

    private Loader loader;
    private List<Item> items;

    @Override
    public void run() {
        List<String> successes = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        IndexCreator.
                createBulkIndex(loader.getClient(), items, Loader.indexName).
                execute().
                actionGet().
                forEach(response -> {
                    if (response.isFailed()) {
                        errors.add(response.getId());
                    } else {
                        successes.add(response.getId());
                    }
                });
        notifyObserver(new Message(items.size(), successes, errors));
    }

    @Override
    public void attachObserver(Loader loader) {
        this.loader = loader;
    }

    @Override
    public void detachObserver(Loader loader) {
        this.loader = null;
    }

    @Override
    public void notifyObserver(Message message) {
        loader.updateStatus(message);
    }
}
