package com.anychart.plugins.GoogleClosurePlugin.managers;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorGutter;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.text.DocumentFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 1:40
 */
public class ContentManager {

    public String getNamespace(Editor editor) {
        VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());
        String text = editor.getDocument().getText();
        String namespace;

        int googProvideOffset = text.indexOf("goog.provide(", 0);
        if (googProvideOffset != -1) {
            namespace = text.substring(googProvideOffset + "goog.provide(".length() + 1, text.indexOf(")", googProvideOffset) - 1);
        } else {
            String filePath = file.getPath();
            String fileName = file.getName();
            int srcIndex = filePath.indexOf("src/") + 4;
            int fileNameIndex = filePath.indexOf(fileName);
            if (srcIndex == fileNameIndex) return fileName.substring(0, fileName.lastIndexOf("."));
            namespace = filePath.substring(
                    srcIndex, fileNameIndex - 1
            ).replaceAll("\\/", ".");

            String cmp1;
            String cmp2 = fileName.substring(0, fileName.lastIndexOf("."));
            int tmp = namespace.lastIndexOf(".", namespace.length());
            cmp1 = tmp > 0 ? namespace.substring(tmp + 1) : "";

            if (tmp == -1) {
                cmp1 = namespace;
            }
            if (!cmp1.equals(cmp2)) {
                namespace += "." + cmp2;
            }
        }

        // use regexps like "goog\.provide\(([a-zA-Z.]+)\)", where you need the shortest $1 value
        return namespace;
    }

    public String getClassName(Editor editor) {
        String className = "";

        int offset = editor.getCaretModel().getOffset();
        int lineNumber = editor.getDocument().getLineNumber(offset);
        Pattern p = Pattern.compile("^((([a-z][a-zA-Z_0-9]+)\\.)+[A-Z][a-zA-Z0-9]+)[ ]*=[ ]*function");

        while (lineNumber != -1) {
            int lineStartOffset = editor.getDocument().getLineStartOffset(lineNumber);
            int lineEndOffset = editor.getDocument().getLineEndOffset(lineNumber);
            TextRange textRange = new TextRange(lineStartOffset, lineEndOffset);
            String stringLine = editor.getDocument().getText(textRange);

            Matcher m = p.matcher(stringLine);

            if (!m.find()) {
                lineNumber -= 1;
            } else {
                className = m.group(1);
                return className;
            }
        }
        if (className.equals("")) return null;
        return className;
        // use regexps like "^((([a-z][a-zA-Z_]+)\.)+[A-Z][a-zA-Z]+)[ ]+=[ ]+function", where you need $3 value, to not
        // to be "prototype." string.
    }

    public void insertString(Document doc, String text, int from, int to) {
        doc.replaceString(from, to, text);
    }

}
