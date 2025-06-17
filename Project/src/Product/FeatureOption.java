package Product;

import Configuration.ConfigurationModels.*;

import java.util.Map;

public class FeatureOption {
    private static final Map<ProductFeatures, Class<? extends Enum<?>>> featureMap = Map.of(
            ProductFeatures.RAM, RamConfiguration.class,
            ProductFeatures.PROCESSOR, ProcessorConfiguration.class,
            ProductFeatures.BATTERYCAPACITY, BatteryCapacityConfiguration.class,
            ProductFeatures.COLOR, ColorConfiguration.class,
            ProductFeatures.ADDITIONALACCRESSORIES, AdditionalAccessoriesConfiguration.class
    );
    public static Class<? extends Enum<?>> getOptionClass(ProductFeatures feature) {
        return featureMap.get(feature);
    }
}
