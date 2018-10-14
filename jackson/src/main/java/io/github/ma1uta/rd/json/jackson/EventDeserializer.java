package io.github.ma1uta.rd.json.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.ma1uta.rd.json.Event;
import io.github.ma1uta.rd.json.Fevent;
import io.github.ma1uta.rd.json.Sevent;

import java.io.IOException;

public class EventDeserializer extends JsonDeserializer<Event> {

    @Override
    public Event deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec codec = p.getCodec();
        JsonNode jsonNode = codec.readTree(p);

        String type = jsonNode.get("type").asText();
        switch (type) {
            case Event.Type.F:
                return codec.treeToValue(jsonNode, Fevent.class);
            case Event.Type.S:
                return codec.treeToValue(jsonNode, Sevent.class);
            default:
                return null;
        }
    }
}
