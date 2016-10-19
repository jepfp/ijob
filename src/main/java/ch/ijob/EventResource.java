package ch.ijob;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.ijob.model.Event;
@Path("/event")
public class EventResource {
    @Inject
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        List<Event> events = readAllEvents();
        StringBuffer buf = new StringBuffer();
        buf.append("<html><head><title>Alle Events</title></head>" //
            + "<body><h1>Alle Events</h1><ul>");
        buf.append(events.stream() //
            .map(e -> "<li>" + e.getText() + " (" + e.getDescription() + ")</li>") //
            .collect(Collectors.joining("\n")));
        buf.append("</ul></body></html>");
        return buf.toString();
    }

    private List<Event> readAllEvents() {
        TypedQuery<Event> q = em.createQuery("from Event", Event.class);
        List<Event> eventList = q.getResultList();
        return eventList;
    }
}
