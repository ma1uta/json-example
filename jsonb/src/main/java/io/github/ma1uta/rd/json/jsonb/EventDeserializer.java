package io.github.ma1uta.rd.json.jsonb;

import io.github.ma1uta.rd.json.Event;
import io.github.ma1uta.rd.json.Fevent;
import io.github.ma1uta.rd.json.Sevent;

import java.lang.reflect.Type;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;

public class EventDeserializer implements JsonbDeserializer<Event> {

    @Override
    public Event deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        JsonObject object = parser.getObject();
        String type = object.getString("type");

        Jsonb jsonb = JsonbBuilder.create();

        switch (type) {
            case Event.Type.F:
                return jsonb.fromJson(object.toString(), Fevent.class);
            case Event.Type.S:
                return jsonb.fromJson(object.toString(), Sevent.class);
            default:
                return null;
        }
    }
}
