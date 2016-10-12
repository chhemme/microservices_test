package de.hemme.bachelorarbeit.collector.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hemme.bachelorarbeit.collector.entity.SystemEvent;
import de.hemme.bachelorarbeit.collector.view.StatusView;
import de.hemme.bachelorarbeit.collector.view.WizardView;
import io.dropwizard.views.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.common.net.UrlEscapers;

import de.hemme.bachelorarbeit.collector.store.CollectorStore;

/**
 * CollectorResource defines the RESTful methods which are offered by this dropwizard application.
 * JAX-RS is used to define methods, paths and media types.
 */
@Path("events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectorResource {

	private final CollectorStore store;

	Logger log = LoggerFactory.getLogger(CollectorResource.class);

	public CollectorResource(CollectorStore store) {
		this.store = store;
	}

//	@Path("ping")
//	@Timed
//	@GET
//	public SystemEvent getPongWizard() {
//		log.debug("ping was called...");
//		return new SystemEvent(005, 001, 001, new Date());
//	}

	@Path("status")
	@GET
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public StatusView getStatusView(){
		return new StatusView(store);
	}

	@Path("{name}")
	@GET
	public Optional<SystemEvent> getWizard(@PathParam("name") String name) {
		return store.get(name);
	}

	@POST
	public Response addWizard(@Valid final SystemEvent systemEvent) throws URISyntaxException {
		store.store(systemEvent);
		return Response.created(new URI("/" + escape(systemEvent.getEventId()))).build();
	}

	private String escape(final String wizardName) {
		return UrlEscapers.urlPathSegmentEscaper().escape(wizardName);
	}

	/**
	 * This method delivers either an fully fledged html page when it's called with a web browser
	 * (Accept: text/html) or it delivers the raw {@link Hipster} json representation when accessed
	 * with header "Accept: application/json".
	 */
	@GET
	@Path("{name}/view")
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public WizardView getWizardView(@PathParam("name") String name) {
		return new WizardView(getWizard(name).get());
	}

}
