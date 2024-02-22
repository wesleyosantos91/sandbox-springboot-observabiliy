package io.github.wesleyosantos91.metric.tag.gauge;

import io.github.wesleyosantos91.metric.tag.CommonMetricDetails;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

public class TaggedGauge extends CommonMetricDetails {

    private final String tagName;

    public TaggedGauge(String name, MeterRegistry registry, String tagName) {
        super(name, registry);
        this.tagName = tagName;
    }

    public void set(String tagValue, double value) {
        Gauge.builder(name, value, Double::doubleValue).tags(Tags.of(tagName, tagValue)).register(registry);
    }
}
