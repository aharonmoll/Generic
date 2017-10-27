package com.gigaspaces.demo;

import com.gigaspaces.metrics.MetricReporterFactory;
import java.util.Properties;

/**
 * Created by aharon on 5/31/15.
 */

public class FileReporterFactory extends MetricReporterFactory<FileReporter> {
    private String path;

    @Override
    public FileReporter create() throws Exception {
        return new FileReporter(this);
    }

    @Override
    public void load(Properties properties) {
        super.load(properties);
        path = properties.getProperty("path");
        if (path == null) {
            throw new RuntimeException("Property [path] must be provided when using file reporter");
        }
    }

    public String getPath() {
        return path;
    }
}