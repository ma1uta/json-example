package io.github.ma1uta.rd.json;

import com.dslplatform.json.CompiledJson;

@CompiledJson
public class SecondContent implements EventContent {

    private Long ts;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }
}
