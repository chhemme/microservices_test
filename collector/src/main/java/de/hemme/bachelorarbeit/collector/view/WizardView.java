package de.hemme.bachelorarbeit.collector.view;

import de.hemme.bachelorarbeit.collector.entity.SystemEvent;
import de.hemme.bachelorarbeit.collector.resource.CollectorResource;
import io.dropwizard.views.View;

/**
 * The WizardView class holds the instance of the entity and the name of the template which is used
 * to render the instance.
 * 
 * See {@link CollectorResource#getWizardView(String)}.
 *
 */
public class WizardView extends View {

	private final SystemEvent systemEvent;

	public WizardView(SystemEvent systemEvent) {
		super("systemEvent.mustache");
		this.systemEvent = systemEvent;
	}

	public SystemEvent getSystemEvent() {
		return systemEvent;
	}

}
