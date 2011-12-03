package com.anychart.plugins.GoogleClosurePlugin.applicationActions;

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
        System.out.println("KJDFJS:DJFKL:JSDF");
        TemplateImpl template = new TemplateImpl("", "$1$", "other");

//        template.setString("$1$");
        template.setDescription("sync edit template");
        template.setToIndent(false);
        template.setToReformat(false);
        template.setToShortenLongNames(false);
        template.parseSegments();
        template.setInline(false);
        template.addVariable("1", "", "", true);

        Project myProject = e.getData(PlatformDataKeys.PROJECT);
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        assert editor != null;
        TemplateManager templateManager = TemplateManager.getInstance(myProject);



        templateManager.startTemplate(editor, template, new TemplateEditingListener() {
            public void beforeTemplateFinished(TemplateState state, Template template) {
            }

            public void templateFinished(Template template, boolean brokenOff) {
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
