package com.frogbyhinter.frognft.web.rest;

import com.frogbyhinter.frognft.web.support.FrogNftRestSupport;
import com.google.common.collect.ImmutableMap;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthRest extends FrogNftRestSupport {

    private final Environment environment;

    public HealthRest(Environment environment) {
        this.environment = environment;
    }

    @GetMapping({"/", "/health"})
    public ResponseEntity health() {
        return success(
                ImmutableMap.of(
                        "health", "OK",
                        "profiles", environment.getActiveProfiles()
                )
        );
    }

}
