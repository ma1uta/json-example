package io.github.ma1uta.rd.json.dsljson;

import io.github.ma1uta.rd.json.AbstractTest;
import io.github.ma1uta.rd.json.Event;
import org.junit.jupiter.api.Test;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

public class DslJsonTest extends AbstractTest {

    @Test
    public void jsonb() {
        Jsonb jsonb = JsonbBuilder.create();

        Event event = jsonb.fromJson(ORIGIN_F, Event.class);

        validateF(event);
    }
}
