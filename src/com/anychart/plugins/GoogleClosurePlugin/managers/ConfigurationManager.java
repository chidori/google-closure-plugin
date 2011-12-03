package com.anychart.plugins.GoogleClosurePlugin.managers;

import com.anychart.plugins.GoogleClosurePlugin.configuration.Configuration;
import com.intellij.openapi.roots.ui.configuration.projectRoot.HistoryAware;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by langley
 */
public class ConfigurationManager {

    private Configuration configuration;

    public ConfigurationManager(){
        XStream xStream = new XStream(new StaxDriver());
        initAliases(xStream);
        InputStream stream = null;
        try {
            configuration = (Configuration) xStream.fromXML(getStream());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }

    }

     public InputStream getStream() {
        if (StringUtils.isNotBlank(System.getProperty("configuration"))) {
            File file = new File(System.getProperty("configuration"));
            if ((file.exists()) && (file.canRead()) && (file.isFile())) {
                try {
                    return new FileInputStream(file);
                } catch (FileNotFoundException ignored) {

                }
            }
        }

        // not found configuration
        {
            throw new IllegalStateException("Configuration not found, use -Dconfiguration=[file name]");
        }
    }

    protected void initAliases(XStream xStream) {
        xStream.alias("configuration", Configuration.class);
    }

    public Configuration getConfiguration() {
        return configuration;
    }



}
