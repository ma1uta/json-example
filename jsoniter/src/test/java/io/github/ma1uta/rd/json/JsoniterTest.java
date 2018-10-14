package io.github.ma1uta.rd.json;

import com.jsoniter.JsonIterator;
import com.jsoniter.extra.JacksonCompatibilityMode;
import com.jsoniter.spi.DecodingMode;
import com.jsoniter.spi.JsoniterSpi;
import io.github.ma1uta.rd.json.jsoniter.EventDeserializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsoniterTest extends AbstractTest {

    @Test
    public void jsoniterF() throws IOException {
        JsoniterSpi.registerExtension(new JacksonCompatibilityMode.Builder().build());
        JsoniterSpi.registerTypeDecoder(Event.class, new EventDeserializer());
        JsonIterator.setMode(DecodingMode.REFLECTION_MODE);
        Event event = JsonIterator.deserialize(ORIGIN_F.getBytes(StandardCharsets.UTF_8), Event.class);

        validateF(event);
    }

    @Test
    public void jsoniterS() throws IOException {
        JsoniterSpi.registerExtension(new JacksonCompatibilityMode.Builder().build());
        JsoniterSpi.registerTypeDecoder(Event.class, new EventDeserializer());
        JsonIterator.setMode(DecodingMode.REFLECTION_MODE);
        Event event = JsonIterator.deserialize(ORIGIN_S.getBytes(StandardCharsets.UTF_8), Event.class);

        validateS(event);
    }
}
