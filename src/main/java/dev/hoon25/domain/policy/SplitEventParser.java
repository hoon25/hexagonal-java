package dev.hoon25.domain.policy;

import dev.hoon25.domain.entity.Event;
import dev.hoon25.domain.vo.Activity;
import dev.hoon25.domain.vo.EventId;
import dev.hoon25.domain.vo.Protocol;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

public class SplitEventParser implements EventParser {
    @Override
    public Event parseEvent(String event) {
        List<String> fields = Arrays.asList(event.split(" "));

        return new Event(
                LocalDateTime.parse(fields.get(0), formatter).atOffset(ZoneOffset.UTC),
                EventId.of(fields.get(1)),
                Protocol.valueOf(fields.get(2)),
                new Activity(fields.get(3), fields.get(5))
        );
    }
}
