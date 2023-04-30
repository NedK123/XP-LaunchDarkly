package com.example.xplaunchdarkly.config;

import com.launchdarkly.sdk.server.Components;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LaunchDarklyConfig {

    @Value("${feature-flagging.app-key}")
    private String appKey;

    @Bean
    public LDClient ldClient(LDConfig ldConfig) {
        return new LDClient(appKey, ldConfig);
    }

    @Bean
    public LDConfig ldConfig() {
        return new LDConfig.Builder()
                .events(Components.sendEvents())
                .build();
    }

}
