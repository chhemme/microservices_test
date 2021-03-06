package de.hemme.bachelorarbeit.collector;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.ImmutableMap;

/**
 * A demo configuration for the {@link CollectorApplication}.
 */
public class CollectorConfig extends Configuration {

	@NotEmpty
	private String greeting;

	private final ImmutableMap<String, ImmutableMap<String, String>> viewConfig = ImmutableMap.of();

	public ImmutableMap<String, ImmutableMap<String, String>> getViewRendererConfiguration() {
		return viewConfig;
	}

	public String getGreeting() {
		return greeting;
	}

}
