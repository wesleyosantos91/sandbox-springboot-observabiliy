package io.github.wesleyosantos91.metric.tag.gauge;

import io.github.wesleyosantos91.metric.tag.CommonMetricDetails;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiTaggedGauge extends CommonMetricDetails {
    List<String> tagNames;

    public MultiTaggedGauge(String name, MeterRegistry registry, String ... tags) {
        super(name, registry);
        this.tagNames = Arrays.asList(tags.clone());
    }

    public void set(double value, String... tagValues){
        List<String> adaptedValues = Arrays.asList(tagValues);
        
        int size = this.tagNames.size();

        if(adaptedValues.size() != size) {
            throw new IllegalArgumentException("Counter tag values mismatch the tag names! " +
                    "Expected args are "+ this.tagNames.toString()+", provided tags were "+adaptedValues);
        }

        List<Tag> tags = new ArrayList<>(size);

        for(int i = 0; i < size; i++) {
            tags.add(new ImmutableTag(this.tagNames.get(i), tagValues[i]));
        }

        Gauge.builder(name, value, Double::doubleValue).tags(tags).register(registry);
    }

}
