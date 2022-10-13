package br.com.company.quarkus.security.deployment;

import br.com.company.quarkus.security.runtime.AuthFilter;
import br.com.company.quarkus.security.runtime.AuthFilterDynamicFeature;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.Capabilities;
import io.quarkus.deployment.Capability;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.resteasy.common.spi.ResteasyJaxrsProviderBuildItem;
import io.quarkus.resteasy.reactive.spi.CustomContainerResponseFilterBuildItem;
import io.quarkus.resteasy.reactive.spi.DynamicFeatureBuildItem;
import io.quarkus.resteasy.reactive.spi.WriterInterceptorBuildItem;
import io.quarkus.vertx.http.deployment.FilterBuildItem;

//Classe utilizada como base: SmallRyeOpenTracingProcessor do projeto smallrye-opentracing

class QuarkusSecurityProcessor {

    //Eu não colocaria quarkus no nome da extensão.
    private static final String FEATURE = "quarkus-security";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    ResteasyJaxrsProviderBuildItem createOpentracingFilter() {
        return new ResteasyJaxrsProviderBuildItem(AuthFilter.class.getName());
    }

    @BuildStep
    public void producer(BuildProducer<AdditionalBeanBuildItem> additionalBeans) {    
    additionalBeans.produce(AdditionalBeanBuildItem.unremovableOf(AuthFilter.class));
    }

    @BuildStep
    void setupFilter(
            BuildProducer<AdditionalBeanBuildItem> additionalBeans,
            BuildProducer<ResteasyJaxrsProviderBuildItem> providers,
            BuildProducer<FilterBuildItem> filterProducer,
            BuildProducer<FeatureBuildItem> feature,
            BuildProducer<CustomContainerResponseFilterBuildItem> customResponseFilters,
            BuildProducer<DynamicFeatureBuildItem> dynamicFeatures,
            BuildProducer<WriterInterceptorBuildItem> writerInterceptors,
            Capabilities capabilities) {
        
        if (capabilities.isPresent(Capability.RESTEASY)) {
            providers.produce(
                    new ResteasyJaxrsProviderBuildItem(AuthFilter.class.getName()));
        } else if (capabilities.isPresent(Capability.RESTEASY_REACTIVE)) {
            customResponseFilters.produce(new CustomContainerResponseFilterBuildItem(
                AuthFilter.class.getName()));
            dynamicFeatures.produce(new DynamicFeatureBuildItem(AuthFilterDynamicFeature.class.getName()));
            // writerInterceptors.produce(
            //         new WriterInterceptorBuildItem.Builder(
            //             AuthFilter.class.getName()).build());
        }
    }

}
