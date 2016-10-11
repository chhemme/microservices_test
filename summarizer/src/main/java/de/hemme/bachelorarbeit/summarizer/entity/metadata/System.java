package de.hemme.bachelorarbeit.summarizer.entity.metadata;

import java.util.List;

public class System {
    private String id;
    private String description;
    private List<Event> events;

    public System(String description, String name, List<Event> events) {
        this.description = description;
        this.id = name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Event> getEvents() {
        return events;
    }
}
