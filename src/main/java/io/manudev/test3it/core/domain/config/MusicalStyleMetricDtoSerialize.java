package io.manudev.test3it.core.domain.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.manudev.test3it.core.domain.dtos.MusicalStyleMetricDto;

import java.io.IOException;

public class MusicalStyleMetricDtoSerialize extends StdSerializer<MusicalStyleMetricDto> {

    public MusicalStyleMetricDtoSerialize(){
        this(null);
    }

    protected MusicalStyleMetricDtoSerialize(Class<MusicalStyleMetricDto> t){
        super(t);
    }


    @Override
    public void serialize(MusicalStyleMetricDto value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("musicalStyleId", value.getMusicalStyleId());
        jgen.writeObjectField("code", value.getCode());
        jgen.writeObjectField("name", value.getName());
        jgen.writeObjectField("totalLikes", value.getTotalLikes());
        jgen.writeEndObject();
    }
}
