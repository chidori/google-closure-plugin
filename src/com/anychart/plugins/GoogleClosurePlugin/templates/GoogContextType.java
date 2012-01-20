package com.anychart.plugins.GoogleClosurePlugin.templates;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 1:55
 */
public class GoogContextType extends TemplateContextType {

    public GoogContextType() {
        super("googleClosure", "Google Closure");
    }

    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        return true;
    }

    @Override
    public boolean isInContext(@NotNull FileType fileType) {
        return true;
    }

}
