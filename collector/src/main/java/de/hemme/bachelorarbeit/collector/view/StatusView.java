package de.hemme.bachelorarbeit.collector.view;

import de.hemme.bachelorarbeit.collector.store.CollectorStore;
import io.dropwizard.views.View;

/**
 * Created by CHHEMME on 12.10.2016.
 */
public class StatusView extends View {

    private final int queueSize;

    public StatusView(int queueSize) {
        super("collectorStore.mustache");
        this.queueSize = queueSize;

    }

    public int getQueueSize() {
        return queueSize;
    }


}
