package de.hemme.bachelorarbeit.collector.store;

import de.hemme.bachelorarbeit.collector.entity.SystemEvent;
import io.dropwizard.lifecycle.Managed;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

/**
 * A dummy store which is backed by a simple HashMap. Used as data store in the WizBook application
 * example.
 */
public class CollectorStore implements Managed {


	final Queue<SystemEvent> internalQueue = new LinkedBlockingQueue<>();
	private boolean running = true;

	public void store(final SystemEvent systemEvent) {
		internalQueue.add(systemEvent);
	}

	public Optional<SystemEvent> getNextSystemEvent() {
		return Optional.fromNullable(internalQueue.poll());
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
		return internalQueue.size();
	}

}
