package com.anychart.plugins.GoogleClosurePlugin.helpers;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.command.CommandProcessor;

/**
 * Date: 2012-01-20
 *
 * @author Anton Kagakin kagakinam@gmail.com
 */
public class RunnableHelper {

    public static void runReadCommand(Project project, Runnable cmd) {
        CommandProcessor.getInstance().executeCommand(project, new ReadAction(cmd), "" , "");
    }

    public static void runWriteCommand(Project project, Runnable cmd) {
        CommandProcessor.getInstance().executeCommand(project, new WriteAction(cmd), "" , "");
    }

    static class ReadAction implements Runnable {
        Runnable cmd;

        ReadAction(Runnable cmd) {
            this.cmd = cmd;
        }

        public void run() {
            ApplicationManager.getApplication().runReadAction(cmd);
        }
    }

    static class WriteAction implements Runnable {
        Runnable cmd;

        WriteAction(Runnable cmd) {
            this.cmd = cmd;
        }

        public void run() {
            ApplicationManager.getApplication().runWriteAction(cmd);
        }
    }

}
