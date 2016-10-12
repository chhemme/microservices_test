package de.hemme.bachelorarbeit.summarizer.store;

import de.hemme.bachelorarbeit.summarizer.SummarizerApplication;
import de.hemme.bachelorarbeit.summarizer.entity.SystemEvent;
import de.hemme.bachelorarbeit.summarizer.resource.SummarizerResource;
import io.dropwizard.lifecycle.Managed;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A dummy store which is backed by a simple HashMap. Used as data store in the WizBook application
 * example.
 */
public class SummarizerStore implements Managed {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(SummarizerApplication.class);


	final HashMap<String, SystemEvent> internalStore = Maps.newHashMap();
	private boolean running = true;

	public void store(final SystemEvent systemEvent) {

		internalStore.put(systemEvent.getEventId(), systemEvent);
		logger.debug("SystemEvent with id " + systemEvent.getEventId() + "stored." );
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

}
