package com.gigaspaces.demo;

import com.gigaspaces.metrics.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by aharon on 5/31/15.
 */
public class FileReporter extends MetricReporter {
    private static final Logger logger = Logger.getLogger(FileReporter.class.getName());
    private static final String EOL = System.getProperty("line.separator");

    private final Date date = new Date();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
    private final File file;

    protected FileReporter(FileReporterFactory factory) {
        super(factory);
        this.file = new File(factory.getPath()).getAbsoluteFile();
    }

    @Override
    public void report(List<MetricRegistrySnapshot> snapshots) {
        StringBuilder sb = new StringBuilder();
        sb.append("Reported at " + formatDateTime(System.currentTimeMillis())).append(EOL);
        for (MetricRegistrySnapshot snapshot : snapshots) {
            sb.append("Sample taken at " + formatDateTime(snapshot.getTimestamp())).append(EOL);

            for (Map.Entry<MetricTagsSnapshot, MetricGroupSnapshot> groupEntry : snapshot.getGroups().entrySet()) {
                sb.append("\tTags: " + groupEntry.getKey()).append(EOL);
                for (Map.Entry<String, Object> metricEntry : groupEntry.getValue().getMetricsValues().entrySet()) {
                    sb.append("\t\t"+metricEntry.getKey() + " => " + metricEntry.getValue()).append(EOL);
                }
            }
        }
        writeToFile(sb.toString());
    }

    private String formatDateTime(long timestamp) {
        date.setTime(timestamp);
        return dateFormatter.format(date);
    }

    private void writeToFile(String text) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
            fw.write(text);
        } catch (IOException e) {
            logger.warning("Failed to write report to file");
        }
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                logger.warning("Failed to close FileWriter");
            }
        }
    }
}