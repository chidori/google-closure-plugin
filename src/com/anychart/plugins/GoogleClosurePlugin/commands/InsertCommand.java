package com.anychart.plugins.GoogleClosurePlugin.commands;

import com.intellij.openapi.editor.Document;

/**
 * Date: 2012-01-23
 *
 * @author Anton Kagakin kagakinam@gmail.com
 */
public class InsertCommand implements Runnable {

    private final int from;
    private final int to;
    private final String text;
    private final Document doc;

    public InsertCommand(Document doc, int from, int to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.doc = doc;
    }

    private void run(int from, int to, String text, Document doc) {
        doc.replaceString(from, to, text);
    }

    public void run() {
        run(from, to, text, doc);
    }
}
