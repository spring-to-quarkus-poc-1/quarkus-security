package br.com.company.quarkus.security.runtime;

import java.util.List;
import java.util.Optional;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "br.com.company.auth", phase = ConfigPhase.BUILD_AND_RUN_TIME_FIXED)
public class FilterConfiguration {
    
    /**
     * URLs permitidas? Tipo whitelist
     */
    @ConfigItem(name = "urls")
    public Optional<List<String>> urls;

}