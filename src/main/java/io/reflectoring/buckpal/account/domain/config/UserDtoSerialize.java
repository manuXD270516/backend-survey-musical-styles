package io.reflectoring.buckpal.account.domain.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.reflectoring.buckpal.account.domain.dtos.UserDto;

import java.io.IOException;

public class UserDtoSerialize extends StdSerializer<UserDto> {

    public UserDtoSerialize(){
        this(null);
    }

    protected UserDtoSerialize(Class<UserDto> t){
        super(t);
    }


    @Override
    public void serialize(UserDto value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeObjectField("fullname", value.getFullname());
        jgen.writeObjectField("emial", value.getEmail());
        jgen.writeObjectField("password", value.getPassword());
        jgen.writeObjectField("phones", value.getPhones());
        jgen.writeEndObject();
    }
}
