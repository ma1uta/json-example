package io.github.ma1uta.rd.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.ma1uta.rd.json.jackson.EventDeserializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class JacksonTest extends AbstractTest {

    @Test
    public void jacksonF() throws IOException {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Event.class, new EventDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Event event = mapper.readValue(ORIGIN_F, Event.class);

        validateF(event);
    }

    @Test
    public void jacksonS() throws IOException {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Event.class, new EventDeserializer());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(module);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Event event = mapper.readValue(ORIGIN_S, Event.class);

        validateS(event);
    }
}
