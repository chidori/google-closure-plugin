package com.anychart.plugins.GoogleClosurePlugin.managers;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 1:40
 */
public class ContentManager {
    public String getCurrentNamespace(){
        // use regexps like "goog\.provide\(([a-zA-Z.]+)\)", where you need the shortest $1 value
        return null;
    }

    public String getCurrentClassName(){
        // use regexps like "^((([a-z][a-zA-Z_]+)\.)+[A-Z][a-zA-Z]+)[ ]+=[ ]+function", where you need $3 value, to not
        // to be "prototype." string.
        return null;
    }
}
