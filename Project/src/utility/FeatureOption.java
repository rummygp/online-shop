package utility;

import interfaces.ConfigurableOptions;
import enums.ProductFeatures;
import enums.*;

import java.util.Map;

public class FeatureOption {
    private static final Map<ProductFeatures, Class<? extends ConfigurableOptions>> featureMap = Map.of(
            ProductFeatures.RAM, RamConfiguration.class,
            ProductFeatures.PROCESSOR, ProcessorConfiguration.class,
            ProductFeatures.BATTERYCAPACITY, BatteryCapacityConfiguration.class,
            ProductFeatures.COLOR, ColorConfiguration.class,
            ProductFeatures.ADDITIONALACCRESSORIES, AdditionalAccessoriesConfiguration.class
    );
    public static Class<? extends ConfigurableOptions> getOptionClass(ProductFeatures feature) {
        return featureMap.get(feature);
    }
}
