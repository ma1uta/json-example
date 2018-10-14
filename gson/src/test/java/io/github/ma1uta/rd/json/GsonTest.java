package io.github.ma1uta.rd.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.ma1uta.rd.json.gson.EventDeserializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GsonTest extends AbstractTest {

    @Test
    public void gsonF() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Event.class, new EventDeserializer()).create();

        Event event = gson.fromJson(ORIGIN_F, Event.class);

        validateF(event);
    }

    @Test
    public void gsonS() throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Event.class, new EventDeserializer()).create();

        Event event = gson.fromJson(ORIGIN_S, Event.class);

        validateS(event);
    }
}
