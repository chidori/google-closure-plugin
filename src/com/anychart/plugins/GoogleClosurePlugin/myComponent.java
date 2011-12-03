package com.anychart.plugins.GoogleClosurePlugin;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.impl.EditorActionManagerImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Author: Anton Kagakin kagakinam@gmail.com
 * Date: 04.12.11
 * Time: 2:32
 */
public class MyComponent implements ApplicationComponent {
    public MyComponent() {
    }

    public void initComponent() {

        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MyComponent";
    }
}
