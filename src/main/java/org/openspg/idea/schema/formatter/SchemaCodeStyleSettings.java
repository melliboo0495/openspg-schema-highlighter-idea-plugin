package org.openspg.idea.schema.formatter;


import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class SchemaCodeStyleSettings extends CustomCodeStyleSettings {

    public SchemaCodeStyleSettings(CodeStyleSettings settings) {
        super("SchemaCodeStyleSettings", settings);
    }

}
