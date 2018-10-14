package io.github.ma1uta.rd.json;

import com.dslplatform.json.Configuration;
import com.dslplatform.json.runtime.FormatDescription;
import com.dslplatform.json.runtime.MixinDescription;

import java.nio.charset.Charset;

public class _Event_DslJsonConverter implements Configuration {
    private static final Charset utf8 = Charset.forName("UTF-8");

    @Override
    public void configure(com.dslplatform.json.DslJson dslJson) {
        MixinDescription<Event> description = new MixinDescription<>(
            Event.class,
            dslJson,
            "type",
            new FormatDescription[] {
                new FormatDescription(Fevent.class,
                    new _Fevent_DslJsonConverter.ObjectFormatConverter(dslJson), null, true, "f", dslJson),
                 new FormatDescription(Fevent.class,
                    new _Sevent_DslJsonConverter.ObjectFormatConverter(dslJson), null, true, "s", dslJson)
            }
        );
        dslJson.registerReader(Event.class, description);
        dslJson.registerWriter(Event.class, description);
    }
}
