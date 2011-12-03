package com.anychart.plugins.GoogleClosurePlugin.templates;

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 1:52
 */
public class GoogProvider implements DefaultLiveTemplatesProvider {

    private final static String DEFAULT_TEMPLATES[] = {
            "/templates/goog",
    };

    public String[] getDefaultLiveTemplateFiles() {
        return DEFAULT_TEMPLATES;
    }

    public String[] getHiddenLiveTemplateFiles() {
        return null;
    }

}
