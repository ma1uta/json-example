package io.github.ma1uta.rd.json;

import com.fasterxml.jackson.annotation.JsonProperty;

//@CompiledJson
public abstract class Event<C extends EventContent> {

    public static class Type {
        public static final String F = "f";

        public static final String S = "s";
    }

    private C content;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public abstract String getType();

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }
}
