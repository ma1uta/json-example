package io.github.ma1uta.rd.json;

import com.dslplatform.json.CompiledJson;

import javax.json.bind.annotation.JsonbProperty;

//@CompiledJson(deserializeDiscriminator = "type", deserializeName = Event.Type.F)
public class Fevent extends Event<FirstContent> {

    @JsonbProperty("room_id")
    private String roomId;

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
