package com.anychart.plugins.GoogleClosurePlugin.managers.syntax;

import com.anychart.plugins.GoogleClosurePlugin.managers.ContentManager;
import com.anychart.plugins.GoogleClosurePlugin.managers.SyntaxManager;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.util.List;

/**
 * Author: Anton Saukh saukham@gmail.com
 * Date: 04.12.11
 * Time: 1:40
 */
public class SyntaxManagerResult {
    public SyntaxManagerResult(String[] requires, String replaceString) {
        this.requires = requires;
        this.replaceString = replaceString;
    }

    public SyntaxManagerResult(Object[] requires, String replaceString) {
        this.requires = new String[requires.length];
        for (int i = 0; i < requires.length; i++) {
            this.requires[i] = (String) requires[i];
        }
        this.replaceString = replaceString;
    }

    private String[] requires;

    public String[] getRequires() {
        return requires;
    }

    public void setRequires(String[] requires) {
        this.requires = requires;
    }

    private String replaceString;

    public String getReplaceString() {
        return replaceString;
    }

    public void setReplaceString(String replaceString) {
        this.replaceString = replaceString;
    }
}
