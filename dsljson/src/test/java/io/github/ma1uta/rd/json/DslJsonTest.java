package io.github.ma1uta.rd.json;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class DslJsonTest extends AbstractTest {

    public static final String ORIGIN_F2 = "{\"type\":\"f\",\"content\":{\"field\":\"data\"},\"room_id\":\"123\"}";

    public static final String ORIGIN_S2 = "{\"type\":\"s\",\"state_key\":\"static\",\"content\":{\"ts\":10}}";

    @Test
    public void dsljsonF() throws IOException {
        DslJson<Object> dslJson = new DslJson<>(Settings.withRuntime().includeServiceLoader());

        Event event = dslJson.deserialize(Event.class, new ByteArrayInputStream(ORIGIN_F2.getBytes(StandardCharsets.UTF_8)));

        validateF(event);
    }

    @Test
    public void dsljsonS() throws IOException {
        DslJson<Object> dslJson = new DslJson<>();

        Event event = dslJson.deserialize(Event.class, new ByteArrayInputStream(ORIGIN_S2.getBytes(StandardCharsets.UTF_8)));

        validateS(event);
    }

    @Test
    public void jsonbF() {
        Jsonb jsonb = JsonbBuilder.create();

        Event event = jsonb.fromJson(ORIGIN_F2, Event.class);

        validateF(event);
    }

    @Test
    public void jsonbS() {
        Jsonb jsonb = JsonbBuilder.create();

        Event event = jsonb.fromJson(ORIGIN_S2, Event.class);

        validateS(event);
    }
}
