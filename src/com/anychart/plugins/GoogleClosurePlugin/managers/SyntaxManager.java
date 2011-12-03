package com.anychart.plugins.GoogleClosurePlugin.managers;

import com.anychart.plugins.GoogleClosurePlugin.managers.syntax.SyntaxManagerResult;

/**
 * Author: Anton Saukh saukham@gmail.com
 * Date: 04.12.11
 * Time: 1:40
 */
public class SyntaxManager {
    public SyntaxManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    private ContentManager contentManager;

    public SyntaxManagerResult parseInput(String inputString) {
        return null;
    }
}
