package io.github.ma1uta.rd.json;

import io.github.ma1uta.rd.json.jsonb.EventDeserializer;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

public class JsonbTest extends AbstractTest {

    @Test
    public void jsonbF() {
        Jsonb jsonb = JsonbBuilder.newBuilder().withConfig(new JsonbConfig().withDeserializers(new EventDeserializer())).build();

        Event event = jsonb.fromJson(ORIGIN_F, Event.class);

        validateF(event);
    }

    @Test
    public void jsonbS() {
        Jsonb jsonb = JsonbBuilder.newBuilder().withConfig(new JsonbConfig().withDeserializers(new EventDeserializer())).build();

        Event event = jsonb.fromJson(ORIGIN_S, Event.class);

        validateS(event);
     }
}
