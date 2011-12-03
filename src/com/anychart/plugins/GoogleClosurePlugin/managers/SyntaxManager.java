package com.anychart.plugins.GoogleClosurePlugin.managers;

import com.anychart.plugins.GoogleClosurePlugin.managers.syntax.SyntaxManagerResult;
import com.intellij.openapi.editor.Editor;

import java.util.ArrayList;

/**
 * Author: Anton Saukh saukham@gmail.com
 * Date: 04.12.11
 * Time: 1:40
 */
public class SyntaxManager {
    public SyntaxManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    private ContentManager contentManager;

    private boolean isPrivate;
    private boolean isProtected;
    private boolean isStatic;

    public SyntaxManagerResult parseInput(String inputString, Editor editor) throws Exception {
        inputString = inputString.trim();
        String[] firstDivision = inputString.split(" ", 2);
        firstDivision[0] = firstDivision[0].trim();
        isPrivate = firstDivision[0].equalsIgnoreCase("private");
        isProtected = firstDivision[0].equalsIgnoreCase("protected");
        if (isPrivate || isProtected || firstDivision[0].equalsIgnoreCase("public")) {
            if (firstDivision.length == 1)
                throw new Exception("Bad input string: expected something more than just \"" + inputString + "\"");
            firstDivision = firstDivision[1].split("  ", 2);
            firstDivision[0] = firstDivision[0].trim();
        }
        isStatic = firstDivision[0].equalsIgnoreCase("static");
        if (isStatic) {
            if (firstDivision.length == 1)
                throw new Exception("Bad input string: expected something more than just \"" + inputString + "\"");
            firstDivision = firstDivision[1].split("  ", 2);
            firstDivision[0] = firstDivision[0].trim();
        }
        if (firstDivision[0].equalsIgnoreCase("class")) {
            return parseClass(firstDivision, editor);
        } else if (firstDivision[0].equalsIgnoreCase("interface")) {
            return parseInterface(firstDivision, editor);
        } else if (firstDivision[0].equalsIgnoreCase("function")) {
            return parseFunction(firstDivision, editor);
        } else if (firstDivision[0].equalsIgnoreCase("var")) {
            return parseProp(firstDivision, editor);
        } else
            throw new Exception("Bad input string: expected class, interface, function or variable definition");
    }

    private SyntaxManagerResult parseClass(String[] firstDivision, Editor editor) throws Exception {
        if (firstDivision.length == 1)
            throw new Exception("Bad input string: expected something more than just \"" + firstDivision[0] + "\"");
        String[] components = firstDivision[1].split(" ", 2);
        String className = components[0].trim();
        String ancestor = null;
        String[] interfaces = null;
        if (components.length > 1) {
            components = components[1].split(" ", 2);
            components[0] = components[0].trim();
            if (components[0].equalsIgnoreCase("extends")) {
                if (components.length == 1)
                    throw new Exception(
                            "Bad input string: class extension needs ancestor name");
                components = components[1].split(" ", 2);
                ancestor = components[0].trim();
                if (components.length > 1)
                    components = components[1].split(" ", 2);
                else
                    components = null;
            }
            if (components != null && components[0].equalsIgnoreCase("implements")) {
                if (components.length == 1)
                    throw new Exception(
                            "Bad input string: class interface implementation needs interface name");
                interfaces = components[1].split(",");
                components = null;
            }
            if (components != null)
                if (ancestor != null)
                    throw new Exception("Bad input string: expected class extending or interface implementation");
                else
                    throw new Exception("Bad input string: expected class interface implementation");
        }
        ArrayList<String> requires = new ArrayList<String>();
        StringBuilder result = new StringBuilder();
        result.append("/**\n");
        result.append(" * TODO: write docs.\n");
        result.append(" * @constructor\n");
        if (isPrivate)
            result.append(" * @private\n");
        if (ancestor != null) {
            result.append(" * @extends {");
            result.append(ancestor);
            result.append("}\n");
            requires.add(getClassNameSpace(ancestor));
        }
        if (interfaces != null)
            for (String anInterface : interfaces) {
                result.append(" * @implements {");
                result.append(anInterface);
                result.append("}\n");
                requires.add(getClassNameSpace(anInterface));
            }
        result.append(contentManager.getNamespace(editor));
        result.append(".");
        result.append(className);
        result.append(" = function() {}\n");
        if (ancestor != null) {
            result.append("goog.inherits(\"");
            result.append(className);
            result.append(", ");
            result.append(ancestor);
            result.append(");\n");
        }
        return new SyntaxManagerResult((String[])requires.toArray(), result.toString());
    }

    private SyntaxManagerResult parseInterface(String[] firstDivision, Editor editor) throws Exception {
        if (firstDivision.length == 1)
            throw new Exception("Bad input string: expected something more than just \"" + firstDivision[0] + "\"");
        String[] components = firstDivision[1].split(" ", 2);
        String interfaceName = components[0].trim();
        String[] interfaces = null;
        if (components.length > 1) {
            components = components[1].split(" ", 2);
            components[0] = components[0].trim();
            if (components[0].equalsIgnoreCase("implements")) {
                if (components.length == 1)
                    throw new Exception(
                            "Bad input string: interface extension needs interface name");
                interfaces = components[1].split(",");
                components = null;
            }
            if (components != null)
                throw new Exception("Bad input string: expected interface extension");
        }
        ArrayList<String> requires = new ArrayList<String>();
        StringBuilder result = new StringBuilder();
        result.append("/**\n");
        result.append(" * TODO: write docs.\n");
        result.append(" * @interface\n");
        if (isPrivate)
            result.append(" * @private\n");
        if (interfaces != null)
            for (String anInterface : interfaces) {
                result.append(" * @extends {");
                result.append(anInterface);
                result.append("}\n");
                requires.add(getClassNameSpace(anInterface));
            }
        result.append(" */");
        result.append(contentManager.getNamespace(editor));
        result.append(".");
        result.append(interfaceName);
        result.append(" = function() {}\n");
        return new SyntaxManagerResult((String[])requires.toArray(), result.toString());
    }

    private SyntaxManagerResult parseFunction(String[] firstDivision, Editor editor) throws Exception {
        if (firstDivision.length == 1)
            throw new Exception("Bad input string: expected something more than just \"" + firstDivision[0] + "\"");
        String[] components = firstDivision[1].split("\\(", 2);

        String functionName = components[0].trim();
        ArrayList<String> paramNames = new ArrayList<String>();
        ArrayList<String> paramTypes = new ArrayList<String>();
        if (components.length == 1)
            throw new Exception("Bad input string: parentheses expected");

        components = components[1].split(",");
        for (int i = 0; i < components.length - 1; i++){
            String[] tmp = components[i].split(":", 2);
            paramNames.add(tmp[0].trim());
            paramTypes.add((tmp.length > 1) ? tmp[1].trim() : "*");
        }
        components = components[components.length - 1].split("\\)", 2);
        if (components[0].trim().length() > 0){
            String[] tmp = components[0].split(":", 2);
            paramNames.add(tmp[0].trim());
            paramTypes.add((tmp.length > 1) ? tmp[1].trim() : "*");
        }
        if (components.length == 1)
            throw new Exception("Bad input string: unterminated parentheses");
        components = components[1].split(":", 2);
        if (components[0].trim().length() != 0)
            throw new Exception("Bad input string: unexpected symbols after \")\"");
        String functionType = (components.length > 1) ? components[1].trim() : "void";

        StringBuilder result = new StringBuilder();
        result.append("/**\n");
        result.append(" * TODO: write docs.\n");
        if (isPrivate)
            result.append(" * @private\n");
        else if (isProtected)
            result.append(" * @protected\n");
        for (int i = 0, len = paramNames.size(); i < len; i++) {
            result.append(" * @param {");
            result.append(paramTypes.get(i));
            result.append("} ");
            result.append(paramNames.get(i));
            result.append(" TODO: param docs.\n");
        }
        if (!functionType.equalsIgnoreCase("void")) {
            result.append(" * @return {");
            result.append(functionType);
            result.append("}\n");
        }
        result.append(" */\n");
        result.append(contentManager.getClassName(editor));
        if (isStatic)
            result.append(".");
        else
            result.append(".prototype.");
        result.append(functionName);
        result.append(" = function(");
        for (int i = 0, len = paramNames.size(); i < len; i++) {
            result.append(paramNames.get(i));
            if (i < len - 1)
                result.append(", ");
        }
        result.append(") {}\n");
        return new SyntaxManagerResult(null, result.toString());
    }

    private SyntaxManagerResult parseProp(String[] firstDivision, Editor editor) throws Exception {
        if (firstDivision.length == 1)
            throw new Exception("Bad input string: expected something more than just \"" + firstDivision[0] + "\"");
        String[] components = firstDivision[1].split(":", 2);
        String propName = components[0].trim();
        String propType = (components.length > 1) ? components[1].trim() : "*";
        StringBuilder result = new StringBuilder();
        result.append("/**\n");
        result.append(" * TODO: write docs.\n");
        if (isPrivate)
            result.append(" * @private\n");
        else if (isProtected)
            result.append(" * @protected\n");
        result.append(" * @type {");
        result.append(propType);
        result.append("}\n */\n");
        result.append(contentManager.getClassName(editor));
        if (isStatic)
            result.append(".");
        else
            result.append(".prototype.");
        result.append(propName);
        result.append(" = null\n");
        return new SyntaxManagerResult(null, result.toString());
    }

    private String getClassNameSpace(String className) {
        String[] temp = className.split(".");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < temp.length - 2; i++) {
            res.append(temp[i]);
            res.append(".");
        }
        return res.toString();
    }
}
