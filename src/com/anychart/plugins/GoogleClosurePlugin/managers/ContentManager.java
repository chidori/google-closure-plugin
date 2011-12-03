package com.anychart.plugins.GoogleClosurePlugin.managers;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorGutter;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.text.DocumentFilter;


/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 1:40
 */
public class ContentManager {

    public String getNamespace(Editor editor) {
        // use regexps like "goog\.provide\(([a-zA-Z.]+)\)", where you need the shortest $1 value
        return null;
    }

    public String getClassName(Editor editor) {
        // use regexps like "^((([a-z][a-zA-Z_]+)\.)+[A-Z][a-zA-Z]+)[ ]+=[ ]+function", where you need $3 value, to not
        // to be "prototype." string.
        return null;
    }

    public void insertString(Document doc, String text, int offset) {

    }

}
