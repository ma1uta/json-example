package io.github.ma1uta.rd.json;

import com.dslplatform.json.CompiledJson;

@CompiledJson
public class FirstContent implements EventContent {

    private String field;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
