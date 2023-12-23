package dev.hoon25.domain.policy;

import dev.hoon25.domain.entity.Event;
import dev.hoon25.domain.vo.Activity;
import dev.hoon25.domain.vo.EventId;
import dev.hoon25.domain.vo.Protocol;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEventParser implements EventParser {

    @Override
    public Event parseEvent(String event) {
        final String regex = "(\\\"[^\\\"]+\\\")|\\S+";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(event);

        List<String> fields = new ArrayList<>();
        while (matcher.find()) {
            fields.add(matcher.group(0));
        }

        return new Event(
                LocalDateTime.parse(matcher.group(0), formatter).atOffset(ZoneOffset.UTC),
                EventId.of(matcher.group(1)),
                Protocol.valueOf(matcher.group(2)),
                new Activity(matcher.group(3), matcher.group(5))
        );


    }
}
