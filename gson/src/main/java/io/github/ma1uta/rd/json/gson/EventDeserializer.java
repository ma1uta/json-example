package io.github.ma1uta.rd.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import io.github.ma1uta.rd.json.Event;
import io.github.ma1uta.rd.json.Fevent;
import io.github.ma1uta.rd.json.Sevent;

import java.lang.reflect.Type;

public class EventDeserializer implements JsonDeserializer<Event> {

    @Override
    public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = (JsonObject) json;
        String type = jsonObject.get("type").getAsString();

        switch (type) {
            case Event.Type.F:
                return context.deserialize(json, Fevent.class);
            case Event.Type.S:
                return context.deserialize(json, Sevent.class);
            default:
                return null;
        }
    }
}
