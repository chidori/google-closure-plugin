package com.anychart.plugins.GoogleClosurePlugin.templates.macro;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Macro;
import com.intellij.codeInsight.template.Result;
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

    public String getDefaultValue() {
        return "";
    }

    public Result calculateResult(@NotNull Expression[] params, ExpressionContext context) {
        int offset = context.getEditor().getCaretModel().getOffset();
        return null;
    }

    public Result calculateQuickResult(@NotNull Expression[] params, ExpressionContext context) {
        return null;
    }

    public LookupElement[] calculateLookupItems(@NotNull Expression[] params, ExpressionContext context) {
        return new LookupElement[0];
    }
}
