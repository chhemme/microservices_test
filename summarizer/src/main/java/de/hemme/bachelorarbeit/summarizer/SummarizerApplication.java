package de.hemme.bachelorarbeit.summarizer;

import de.hemme.bachelorarbeit.summarizer.entity.STATE_TYPE;
import de.hemme.bachelorarbeit.summarizer.resource.SummarizerResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.servlets.tasks.Task;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMultimap;

import de.hemme.bachelorarbeit.summarizer.entity.SystemEvent;
import de.hemme.bachelorarbeit.summarizer.health.SummarizerHealth;
import de.hemme.bachelorarbeit.summarizer.store.SummarizerStore;

/**
 * Main entry point of the application. In the initialize()-method new bundles are registered. In
 * the run()-method the application is wired together.
 *
 */
public class SummarizerApplication extends Application<SummarizerConfig> {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(SummarizerApplication.class);

	public static void main(String[] args) throws Exception {
		new SummarizerApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<SummarizerConfig> bootstrap) {

		bootstrap.addBundle(new AssetsBundle("/static-content", "/static", "index.html"));

		bootstrap.addBundle(new ViewBundle<SummarizerConfig>() {
			@Override
			public Map<String, Map<String, String>> getViewConfiguration(SummarizerConfig config) {
				return config.getViewRendererConfiguration();
			}
		});
	}

	@Override
	public void run(SummarizerConfig conf, Environment environment) throws Exception {
		logger.info("starting Summarizer");

		SummarizerStore store = new SummarizerStore();
		logger.debug("store created");

		environment.jersey().register(new SummarizerResource(store));

		// Alternative registration of resources by package scan
		// (only possible with no-arg-constructors in all resources)
		// environment.jersey().packages("de.fb.demo.resource");

		environment.lifecycle().manage(store);

		environment.healthChecks().register("WBHealth", new SummarizerHealth());
		logger.debug("healthcheck created");

		environment.admin().addTask(new HelloTask("Hello"));


		//Example entries

		store.store(new SystemEvent("TXP06/in", "TBI", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TXP06/out", "TBI", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TXP06/in", "VMS", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("CC07/out", "VMS", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TSO11/out", "VMS", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("CC07/in", "TBI", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("CC07/out", "TBI", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TSO11/in", "TBI", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TSO11/out", "TBI", "Auctionbooking", 1, new Date(), STATE_TYPE.SUCCESS ));

		store.store(new SystemEvent("TXP06/in", "TBI", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TXP06/out", "TBI", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TXP06/in", "VMS", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("CC07/out", "VMS", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TSO11/out", "VMS", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("CC07/in", "TBI", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("CC07/out", "TBI", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TSO11/in", "TBI", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));
		store.store(new SystemEvent("TSO11/out", "TBI", "Auctionbooking", 2, new Date(), STATE_TYPE.SUCCESS ));

	}

	/**
	 * A simple task. It's added in the {@link SummarizerApplication#run(String...)} method. You can
	 * execute it with a POST on /tasks/Hello.
	 * 
	 */
	private class HelloTask extends Task {

		protected HelloTask(String name) {
			super(name);
		}

		@Override
		public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
			output.println("Hello Task!!");
		}

	}

}
