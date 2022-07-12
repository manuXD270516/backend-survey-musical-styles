package io.manudev.test3it.core.domain.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.manudev.test3it.core.domain.dtos.MusicalStyleDto;

import java.io.IOException;

public class MusicalStyleDtoSerialize extends StdSerializer<MusicalStyleDto> {

    public MusicalStyleDtoSerialize(){
        this(null);
    }

    protected MusicalStyleDtoSerialize(Class<MusicalStyleDto> t){
        super(t);
    }


    @Override
    public void serialize(MusicalStyleDto value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("musicalStyleId", value.getMusicalStyleId());
        jgen.writeObjectField("code", value.getCode());
        jgen.writeObjectField("name", value.getName());
        jgen.writeObjectField("description", value.getDescription());
        jgen.writeEndObject();
    }
}
