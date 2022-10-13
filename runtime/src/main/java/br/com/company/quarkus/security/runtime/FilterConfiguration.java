package br.com.company.quarkus.security.runtime;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

@ConfigMapping(prefix = "br.com.company.auth")      
@ConfigRoot(phase = ConfigPhase.BUILD_TIME)   
public interface FilterConfiguration {

    /**
     * Enable logging to a file.
     */
    @WithDefault("true")
    boolean enable();

    /**
     * The log format.
     */
    @WithDefault("%d{yyyy-MM-dd HH:mm:ss,SSS} %h %N[%i] %-5p [%c{1.}] (%t) %s%e%n")
    String format();
}
