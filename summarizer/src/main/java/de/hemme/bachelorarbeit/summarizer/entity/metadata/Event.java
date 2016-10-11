package de.hemme.bachelorarbeit.summarizer.entity.metadata;

public class Event {
    private String id;
    private String description;

    public Event(String description, String name) {
        this.description = description;
        this.id = name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
