package com.machine.app.iam.config.serializer;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class LongMixin {
    @SuppressWarnings("unused")
    @JsonProperty("long")
    Long value;
}
