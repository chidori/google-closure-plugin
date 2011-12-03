package com.anychart.plugins.GoogleClosurePlugin.applicationActions;

import com.anychart.plugins.GoogleClosurePlugin.managers.ContentManager;
import com.anychart.plugins.GoogleClosurePlugin.managers.SyntaxManager;
import com.anychart.plugins.GoogleClosurePlugin.managers.syntax.SyntaxManagerResult;
import com.intellij.codeInsight.template.Template;
import com.intellij.codeInsight.template.TemplateEditingListener;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.impl.TemplateImpl;
import com.intellij.codeInsight.template.impl.TemplateState;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;

import javax.xml.soap.Text;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 2:19
 */
public class Insert extends AnAction {

    /*@Override
    public void update(AnActionEvent e) {
        super.update(e);
    }*/

    public void actionPerformed(AnActionEvent e) {
        TemplateImpl template = new TemplateImpl("", "$1$", "other");

        template.setDescription("sync edit template");
        template.setToIndent(false);
        template.setToReformat(false);
        template.setToShortenLongNames(false);
        template.parseSegments();
        template.setInline(false);
        template.addVariable("1", "", "", true);

        Project myProject = e.getData(PlatformDataKeys.PROJECT);
        final Editor editor = e.getData(PlatformDataKeys.EDITOR);
        assert editor != null;

        final int replaceFrom = editor.getCaretModel().getOffset();
        final ContentManager contentManager = new ContentManager();
        final SyntaxManager syntaxManager = new SyntaxManager(contentManager);
        TemplateManager templateManager = TemplateManager.getInstance(myProject);

        templateManager.startTemplate(editor, template, new TemplateEditingListener() {
            public void beforeTemplateFinished(TemplateState state, Template template) {

            }

            public void templateFinished(Template template, boolean brokenOff) {
                int replaceTo = editor.getCaretModel().getOffset();
                TextRange textRange = new TextRange(replaceFrom, replaceTo);
                String myString = editor.getDocument().getText(textRange);
                System.out.println(myString);
                SyntaxManagerResult parsedString = syntaxManager.parseInput(myString);
                contentManager.insertString(editor.getDocument(), "chidori", replaceFrom, replaceTo);
//                editor.getDocument().replaceString(replaceFrom, replaceTo, parsedString.toString());
                System.out.println("TEMPLATE FINISHED");
            }

            public void templateCancelled(Template template) {
            }

            public void currentVariableChanged(TemplateState templateState, Template template, int oldIndex, int newIndex) {
            }

            public void waitingForInput(Template template) {
            }
        });
    }
}
