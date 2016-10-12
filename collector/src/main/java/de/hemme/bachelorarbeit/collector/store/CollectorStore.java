package de.hemme.bachelorarbeit.collector.store;

import de.hemme.bachelorarbeit.collector.entity.SystemEvent;
import io.dropwizard.lifecycle.Managed;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

/**
 * A dummy store which is backed by a simple HashMap. Used as data store in the WizBook application
 * example.
 */
public class CollectorStore implements Managed {

	final HashMap<String, SystemEvent> internalStore = Maps.newHashMap();
	private boolean running = true;

	public void store(final SystemEvent systemEvent) {

		internalStore.put(systemEvent.getEventId(), systemEvent);
	}

	public Optional<SystemEvent> get(final String name) {
		return Optional.fromNullable(internalStore.get(name));
	}

	@Override
	public void start() throws Exception {
		running = true;
	}

	@Override
	public void stop() throws Exception {
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

	public int getStoreSize(){
		return internalStore.size();
	}

}
