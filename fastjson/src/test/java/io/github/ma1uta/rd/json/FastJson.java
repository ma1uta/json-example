package io.github.ma1uta.rd.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.github.ma1uta.rd.json.gson.EventDeserializer;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FastJson extends AbstractTest {

    @Test
    public void fastjsonF() throws IOException {
        ParserConfig.getGlobalInstance().putDeserializer(Event.class, new EventDeserializer());
        Event event = JSON.parseObject(ORIGIN_F, Event.class);

        validateF(event);
    }

    @Test
    public void fastjsonS() throws IOException {
        ParserConfig.getGlobalInstance().putDeserializer(Event.class, new EventDeserializer());
        Event event = JSON.parseObject(ORIGIN_S, Event.class);

        validateS(event);
    }
}
