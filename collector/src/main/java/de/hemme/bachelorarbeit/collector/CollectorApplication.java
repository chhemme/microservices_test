package de.hemme.bachelorarbeit.collector;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.servlets.tasks.Task;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.io.PrintWriter;
import java.util.Date;

import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;

import de.hemme.bachelorarbeit.collector.entity.SystemEvent;
import de.hemme.bachelorarbeit.collector.health.CollectorHealth;
import de.hemme.bachelorarbeit.collector.resource.CollectorResource;
import de.hemme.bachelorarbeit.collector.store.CollectorStore;

/**
 * Main entry point of the application. In the initialize()-method new bundles are registered. In
 * the run()-method the application is wired together.
 *
 */
public class CollectorApplication extends Application<CollectorConfig> {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(CollectorApplication.class);

	public static void main(String[] args) throws Exception {
		new CollectorApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<CollectorConfig> bootstrap) {

		bootstrap.addBundle(new AssetsBundle("/static-content", "/static", "index.html"));

		bootstrap.addBundle(new ViewBundle<CollectorConfig>() {
			@Override
			public ImmutableMap<String, ImmutableMap<String, String>> getViewConfiguration(CollectorConfig config) {
				return config.getViewRendererConfiguration();
			}
		});
	}

	@Override
	public void run(CollectorConfig conf, Environment environment) throws Exception {
		logger.info(conf.getGreeting() + ", starting WizBook...");

		CollectorStore store = new CollectorStore();

		environment.jersey().register(new CollectorResource(store));

		// Alternative registration of resources by package scan
		// (only possible with no-arg-constructors in all resources)
		// environment.jersey().packages("de.fb.demo.resource");

		environment.lifecycle().manage(store);

		environment.healthChecks().register("WBHealth", new CollectorHealth());

		environment.admin().addTask(new HelloTask("Hello"));

		// Create two entries
		store.store(new SystemEvent(001, 001, 001, new Date()));
		store.store(new SystemEvent(002, 001, 001, new Date()));
		store.store(new SystemEvent(003, 001, 001, new Date()));
//		store.store(new SystemEvent("Nachricht01", "0800245"));
//		store.store(new SystemEvent("Nachricht02", "Testnachricht"));
	}

	/**
	 * A simple task. It's added in the {@link CollectorApplication#run(String...)} method. You can
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
