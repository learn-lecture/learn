package org.study.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.study.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key;
    private final Response<?> response;
}
