package de.hemme.bachelorarbeit.summarizer.entity.metadata;

import de.hemme.bachelorarbeit.summarizer.entity.SystemEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by CHHEMME on 11.10.2016.
 */
public class MetadataHelper {
    private List<System> systems;
    private List<Event>  events;
    private List<EventFlow> eventFlows;

    public MetadataHelper() {

    }

    public List<System> getSystems() {
        return systems;
    }

    public List<Event> getEvents() {
        return events;
    }

    public List<EventFlow> getEventFlows() {
        return eventFlows;
    }

    public Optional<System> getSystem(String systemID){
        for(System s : systems){
            if (s.getId() == systemID){
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    private List<System> parseSystems(){
        List<System> systemList = new ArrayList<>();
        //TBI
        List<Event> tbiEvents = new ArrayList<>();
        tbiEvents.add(new Event("", "TXP06/in"));
        tbiEvents.add(new Event("", "CC07/in"));
        tbiEvents.add(new Event("", "CC07/out"));
        tbiEvents.add(new Event("", "TSO11/in"));
        tbiEvents.add(new Event("", "TSO11/out"));

        System tbi = new System("", "TBI", tbiEvents  );
        systemList.add(tbi);

        //VMS
        List<Event> vmsEvents = new ArrayList<>();
        vmsEvents.add(new Event("", "TXP06/in"));
        vmsEvents.add(new Event("", "CC07/out"));
        vmsEvents.add(new Event("", "TSO11/out"));

        System vms = new System("", "VMS", vmsEvents  );
        systemList.add(vms);

        return systemList;

        //TODO: parse from JSON
//        Gson g = new Gson();
//
//        System system = g.fromJson(file, Person.class);
    }

    public boolean validEvent(SystemEvent systemEvent){
        Optional<System> systemOptional = getSystem(systemEvent.getSystemId());
        if(systemOptional.isPresent()) {
            //TODO: Weitere Prüfungen
            return true;
        }
        return false;

    }
}
