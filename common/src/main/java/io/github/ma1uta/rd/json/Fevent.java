package io.github.ma1uta.rd.json;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.json.bind.annotation.JsonbProperty;

@CompiledJson(deserializeDiscriminator = "type", deserializeName = Event.Type.F)
public class Fevent extends Event<FirstContent> {

    @JsonbProperty("room_id")
    @JsonProperty("room_id")
    @SerializedName("room_id")
    private String roomId;

    @JsonAttribute(name = "room_id")
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String getType() {
        return Type.F;
    }
}
