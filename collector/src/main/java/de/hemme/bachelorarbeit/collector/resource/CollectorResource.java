package de.hemme.bachelorarbeit.collector.resource;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hemme.bachelorarbeit.collector.entity.SystemEvent;
import de.hemme.bachelorarbeit.collector.view.StatusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.net.UrlEscapers;

import de.hemme.bachelorarbeit.collector.store.CollectorStore;

/**
 * CollectorResource defines the RESTful methods which are offered by this dropwizard application.
 * JAX-RS is used to define methods, paths and media types.
 */
@Path("systemEvents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectorResource {

	private final CollectorStore store;

	Logger logger = LoggerFactory.getLogger(CollectorResource.class);

	public CollectorResource(CollectorStore store) {
		this.store = store;
	}

	@Path("status")
	@GET
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON })
	public StatusView getStatusView(){
		return new StatusView(store.getStoreSize());
	}

	@Path("next")
	@GET
	public SystemEvent getNextSystemEvent(){
		return store.getNextSystemEvent().get();
	}

	@POST
	public Response addSystemEvent(@Valid final SystemEvent systemEvent) throws URISyntaxException {
		store.store(systemEvent);
		return Response.created(new URI("/" + escape(systemEvent.getEventId()))).build();
	}

	private String escape(final String name) {
		return UrlEscapers.urlPathSegmentEscaper().escape(name);
	}

}
