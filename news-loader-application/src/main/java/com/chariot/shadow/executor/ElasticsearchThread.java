package com.chariot.shadow.executor;

import com.chariot.shadow.Loader;
import com.chariot.shadow.item.Item;
import com.chariot.shadow.news.IndexCreator;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Trung Vu on 2017/06/14.
 */
@Getter
@RequiredArgsConstructor
public class ElasticsearchThread implements Runnable, Notifier<Loader, Message> {

    @NonNull
    private Optional<Loader> loader;
    @NonNull
    private List<Item> items;

    @Override
    public void run() {
        List<String> successes = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        if (loader.isPresent()) {
            IndexCreator.
                    createBulkIndex(loader.get().getClient(), items, Loader.INDEX_NAME).
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
    }

    @Override
    public void attachObserver(Loader loader) {
        this.loader = Optional.of(loader);
    }

    @Override
    public void detachObserver(Loader loader) {
        this.loader = Optional.empty();
    }

    @Override
    public void notifyObserver(Message message) {
        if (loader.isPresent()) {
            loader.get().updateStatus(message);
        }
    }
}
