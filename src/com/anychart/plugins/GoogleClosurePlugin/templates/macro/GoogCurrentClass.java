package com.anychart.plugins.GoogleClosurePlugin.templates.macro;

import com.anychart.plugins.GoogleClosurePlugin.managers.ContentManager;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.*;
import org.jetbrains.annotations.NotNull;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 7:02
 */
public class GoogCurrentClass implements Macro {
    public String getName() {
        return "googCurrentClass";
    }

    public String getDescription() {
        return "googCurrentClass()";
    }

    public String getDefaultValue() {
        return "";
    }

    public Result calculateResult(@NotNull Expression[] params, ExpressionContext context) {
        ContentManager contentManager = new ContentManager();
        String className = contentManager.getClassName(context.getEditor());
//        int offset = context.getEditor().getCaretModel().getOffset();
        return new TextResult(className);
    }

    public Result calculateQuickResult(@NotNull Expression[] params, ExpressionContext context) {
        return null;
    }

    public LookupElement[] calculateLookupItems(@NotNull Expression[] params, ExpressionContext context) {
        return new LookupElement[0];
    }
}
