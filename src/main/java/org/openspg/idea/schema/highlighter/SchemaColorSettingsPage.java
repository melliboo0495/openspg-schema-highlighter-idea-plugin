package org.openspg.idea.schema.highlighter;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openspg.idea.schema.SchemaIcons;

import javax.swing.*;
import java.util.Map;

final class SchemaColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Base//Keywords", SchemaSyntaxHighlighter.KEYWORDS),
            new AttributesDescriptor("Base//Operation", SchemaSyntaxHighlighter.OPERATION_SIGN),
            new AttributesDescriptor("Schema//Name", SchemaSyntaxHighlighter.CLASS_NAME),
            new AttributesDescriptor("Schema//Alias name", SchemaSyntaxHighlighter.CLASS_ALIAS),
            new AttributesDescriptor("Schema//Type", SchemaSyntaxHighlighter.CLASS_REFERENCE),
            new AttributesDescriptor("Others//Comment", SchemaSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Others//Error", SchemaSyntaxHighlighter.ERROR),
    };

    @Override
    public Icon getIcon() {
        return SchemaIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new SchemaSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                namespace SampleDoc
                
                # 定义文本块
                Chunk(文本块): EntityType
                    properties:
                        content(内容): Text
                            index: TextAndVector

                # 定义科室
                HospitalDepartment(科室): ConceptType
                    hypernymPredicate: isA
                HumanBodyPart(人体部位): ConceptType
                    hypernymPredicate: isA
                Medicine(药品): EntityType
                    properties:
                        desc(描述): Text
                            index: Text
                        semanticType(语义类型): Text
                            index: Text
                Symptom(症状): EntityType
                     properties:
                        desc(描述): Text
                            index: Text
                        semanticType(语义类型): Text
                            index: Text
                
                Indicator(医学指征): EntityType
                    properties:
                        desc(描述): Text
                            index: Text
                        semanticType(语义类型): Text
                            index: Text
                
                Disease(疾病): EntityType
                    properties:
                        desc(描述): Text
                            index: Text
                        semanticType(语义类型): Text
                            index: Text
                        complication(并发症): Disease
                            constraint: MultiValue
                        commonSymptom(常见症状): Symptom
                            constraint: MultiValue
                        applicableMedicine(适用药品): Medicine
                            constraint: MultiValue
                        hospitalDepartment(就诊科室): HospitalDepartment
                            constraint: MultiValue
                        diseaseSite(发病部位): HumanBodyPart
                            constraint: MultiValue
                    relations:
                        abnormal(异常指征): Indicator
                
                Others(其他): EntityType
                    properties:
                        desc(描述): Text
                            index: TextAndVector
                        semanticType(语义类型): Text
                            index: Text
                
                Space(场地): EntityType
                
                CableProtectionPipe(电缆保护管): EntityType
                
                SpecialLightingCable(专用照明电缆): EntityType
                
                Runway(跑道) -> Space:
                    relations:
                        contains(包含): CableProtectionPipe
                        contains(包含): SpecialLightingCable""";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Override
    public AttributesDescriptor @NotNull [] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @Override
    public ColorDescriptor @NotNull [] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "OpenSPG Schema";
    }

}
