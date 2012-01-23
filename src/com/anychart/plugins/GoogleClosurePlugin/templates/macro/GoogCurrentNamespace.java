package com.anychart.plugins.GoogleClosurePlugin.templates.macro;

import com.anychart.plugins.GoogleClosurePlugin.managers.ContentManager;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.*;
import org.jetbrains.annotations.NotNull;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 1:57
 */
public class GoogCurrentNamespace implements Macro {
    public String getName() {
        return "googCurrentNamespace";
    }

    public String getDescription() {
        return "googCurrentNamespace()";
    }

    public String getPresentableName() {
        return "googCurrentNamespace()";
    }

    public String getDefaultValue() {
        return "";
    }

    public Result calculateResult(@NotNull Expression[] params, ExpressionContext context) {
        ContentManager contentManager = new ContentManager();
        String namespace = contentManager.getNamespace(context.getEditor());
//        int offset = context.getEditor().getCaretModel().getOffset();
        return new TextResult(namespace);
    }

    public Result calculateQuickResult(@NotNull Expression[] params, ExpressionContext context) {
        return calculateResult(params, context);
    }

    public LookupElement[] calculateLookupItems(@NotNull Expression[] params, ExpressionContext context) {
        return new LookupElement[0];
    }
}
