package de.hemme.bachelorarbeit.collector.view;

import de.hemme.bachelorarbeit.collector.store.CollectorStore;
import io.dropwizard.views.View;

/**
 * Created by CHHEMME on 12.10.2016.
 */
public class StatusView extends View {

    private final CollectorStore collectorStore;

    public StatusView(CollectorStore collectorStore) {
        super("collectorStore.mustache");
        this.collectorStore = collectorStore;

    }

    public CollectorStore getCollectorStore() {
        return collectorStore;
    }


}
