package io.github.ma1uta.rd.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractTest {

    public static final String ORIGIN_F = "{\"content\":{\"field\":\"data\"},\"room_id\":\"123\",\"type\":\"f\"}";

    public static final String ORIGIN_S = "{\"state_key\":\"static\",\"type\":\"s\",\"content\":{\"ts\":10}}";

    protected void validateF(Event event) {
        assertNotNull(event);
        assertTrue(event instanceof Fevent);

        Fevent fevent = (Fevent) event;

        assertEquals("123", fevent.getRoomId());
        assertEquals("f", fevent.getType());

        FirstContent content = fevent.getContent();

        assertNotNull(content);
        assertEquals("data", content.getField());
    }

    protected void validateS(Event event) {
        assertNotNull(event);
        assertTrue(event instanceof Sevent);

        Sevent fevent = (Sevent) event;

        assertEquals("static", fevent.getStateKey());
        assertEquals("s", fevent.getType());

        SecondContent content = fevent.getContent();

        assertNotNull(content);
        assertEquals(10L, content.getTs().longValue());
    }
}
