package io.github.ma1uta.rd.json.jsoniter;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.Decoder;
import io.github.ma1uta.rd.json.Event;
import io.github.ma1uta.rd.json.Fevent;
import io.github.ma1uta.rd.json.Sevent;

import java.io.IOException;

public class EventDeserializer implements Decoder {

    @Override
    public Object decode(JsonIterator iter) throws IOException {
        Any any = iter.readAny();
        String type = any.get("type").toString();

        switch (type) {
            case Event.Type.F:
                return any.as(Fevent.class);
            case Event.Type.S:
                return any.as(Sevent.class);
            default:
                return null;
        }
    }
}
