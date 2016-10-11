package de.hemme.bachelorarbeit.summarizer;

import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * A demo configuration for the {@link SummarizerApplication}.
 */
public class SummarizerConfig extends Configuration {

	@NotEmpty
	private String greeting;

	private final Map<String, Map<String, String>> viewConfig = ImmutableMap.of();

	{
	};

	public Map<String, Map<String, String>> getViewRendererConfiguration() {
		return viewConfig;
	}

	public String getGreeting() {
		return greeting;
	}

}
