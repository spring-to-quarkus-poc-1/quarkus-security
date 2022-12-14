package br.com.company.quarkus.security.runtime;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import io.quarkus.arc.Priority;

@Priority(Priorities.AUTHORIZATION)
@PreMatching
@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    Logger logger;

    @Inject
    FilterConfiguration configuration;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String xAuth = requestContext.getHeaderString("x-auth");
        logger.debugf("AUTH FILTER: %s - %s", configuration.urls, xAuth);
        if (xAuth == null || xAuth.isEmpty()) {
            requestContext.abortWith(Response.status(
                    Response.Status.UNAUTHORIZED).build());
            return; 
        }
    }

}
