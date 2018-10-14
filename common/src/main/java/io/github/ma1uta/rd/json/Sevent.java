package io.github.ma1uta.rd.json;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.json.bind.annotation.JsonbProperty;

@CompiledJson(deserializeDiscriminator = "type", deserializeName = Event.Type.S)
public class Sevent extends Event<SecondContent> {

    @JsonbProperty("state_key")
    @JsonProperty("state_key")
    @SerializedName("state_key")
    @JsonAttribute(name = "state_key")
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
