package dev.hoon25.domain.entity;

import dev.hoon25.domain.policy.EventParser;
import dev.hoon25.domain.vo.Activity;
import dev.hoon25.domain.vo.EventId;
import dev.hoon25.domain.vo.Protocol;

import java.time.OffsetDateTime;
import java.util.Objects;

public class Event implements Comparable<Event> {

    private OffsetDateTime timestamp;
    private EventId id;
    private Protocol protocol;
    private Activity activity;

    public Event(OffsetDateTime timestamp, EventId id, Protocol protocol, Activity activity) {
        this.timestamp = timestamp;
        this.id = id;
        this.protocol = protocol;
        this.activity = activity;
    }

    public static Event parsedEvent(String unparsedEvent, EventParser policy) {
        return policy.parseEvent(unparsedEvent);
    }

    @Override
    public int compareTo(Event event) {
        return timestamp.compareTo(event.timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(timestamp, event.timestamp) && Objects.equals(id, event.id) && protocol == event.protocol && Objects.equals(activity, event.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, id, protocol, activity);
    }
}
