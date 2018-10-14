package io.github.ma1uta.rd.json;

import com.dslplatform.json.CompiledJson;

import javax.json.bind.annotation.JsonbProperty;

//@CompiledJson(deserializeDiscriminator = "type", deserializeName = Event.Type.S)
public class Sevent extends Event<SecondContent> {

    @JsonbProperty("state_key")
    private String stateKey;

    public String getStateKey() {
        return stateKey;
    }

    public void setStateKey(String stateKey) {
        this.stateKey = stateKey;
    }

    @Override
    public String getType() {
        return Type.S;
    }
}
