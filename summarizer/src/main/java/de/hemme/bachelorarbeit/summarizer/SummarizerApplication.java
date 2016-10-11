package de.hemme.bachelorarbeit.summarizer;

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
		logger.info(conf.getGreeting() + ", starting WizBook...");

		SummarizerStore store = new SummarizerStore();

		environment.jersey().register(new SummarizerResource(store));

		// Alternative registration of resources by package scan
		// (only possible with no-arg-constructors in all resources)
		// environment.jersey().packages("de.fb.demo.resource");

		environment.lifecycle().manage(store);

		environment.healthChecks().register("WBHealth", new SummarizerHealth());

		environment.admin().addTask(new HelloTask("Hello"));

		// Create two entries
		store.store(new SystemEvent(001, 001, 001, new Date()));
		store.store(new SystemEvent(002, 001, 001, new Date()));
		store.store(new SystemEvent(003, 001, 001, new Date()));
//		store.store(new SystemEvent("Nachricht01", "0800245"));
//		store.store(new SystemEvent("Nachricht02", "Testnachricht"));
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
