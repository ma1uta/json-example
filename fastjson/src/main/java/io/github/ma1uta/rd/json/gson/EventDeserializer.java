package io.github.ma1uta.rd.json.gson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import io.github.ma1uta.rd.json.Event;
import io.github.ma1uta.rd.json.Fevent;
import io.github.ma1uta.rd.json.Sevent;

import java.lang.reflect.Type;

public class EventDeserializer implements ObjectDeserializer {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONObject jsonObject = parser.parseObject();
        String type1 = jsonObject.getString("type");

        switch (type1) {
            case Event.Type.F:
                return (T) jsonObject.toJavaObject(Fevent.class);
            case Event.Type.S:
                return (T) jsonObject.toJavaObject(Sevent.class);
            default:
                return null;
        }

    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.FIELD_NAME;
    }
}
