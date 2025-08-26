package com.mathews.recommender.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Duration;

public class DurationHoursSerializer extends JsonSerializer<Duration> {
    @Override
    public void serialize(Duration duration, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        if (duration == null) {
            generator.writeNull();
        } else {
            generator.writeNumber(duration.toHours());
        }
    }
}