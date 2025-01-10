package org.openspg.idea.schema.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.lang.psi.*;

import java.util.ArrayList;
import java.util.List;

public class SchemaPsiImplUtil {

    public static String getValue(SchemaNamespace element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.TEXT);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(SchemaEntity element) {
        return element.getEntityInfo().getEntityName();
    }

    public static String getEntityName(SchemaEntityInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        if (valueNode != null) {
            return unwrapText(valueNode.getText());
        } else {
            return null;
        }
    }

    public static String getEntityAliasName(SchemaEntityInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.ENTITY_ALIAS_NAME);
        if (valueNode != null) {
            return unwrapText(valueNode.getText());
        } else {
            return null;
        }
    }

    public static List<String> getEntityClassList(SchemaEntityInfo element) {
        List<String> classList = new ArrayList<>();
        for (PsiElement child : element.getChildren()) {
            IElementType type = child.getNode().getElementType();
            if (type == SchemaTypes.ENTITY_BUILDIN_CLASS || type == SchemaTypes.ENTITY_CLASS) {
                classList.add(unwrapText(child.getText()));
            }
        }
        return classList;
    }

    public static String getName(SchemaEntityInfo element) {
        return getEntityName(element);
    }

    public static PsiElement setName(SchemaEntityInfo element, String newName) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        if (valueNode != null) {
            System.out.println("entity name: " + element.getNode().getElementType());
            //SimpleProperty property =
            //        SimpleElementFactory.createProperty(element.getProject(), newName);
            //ASTNode newKeyNode = property.getFirstChild().getNode();
            //element.getNode().replaceChild(valueNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SchemaEntityInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        return valueNode != null ? valueNode.getPsi() : null;
    }

    public static String getName(SchemaEntityMetaInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getValue(SchemaEntityMetaInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.TEXT);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getPropertyName(SchemaPropertyInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.PROPERTY_NAME);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getPropertyAliasName(SchemaPropertyInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.PROPERTY_ALIAS_NAME);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static List<String> getPropertyClassList(SchemaPropertyInfo element) {
        List<String> classList = new ArrayList<>();
        for (PsiElement child : element.getChildren()) {
            IElementType type = child.getNode().getElementType();
            if (type == SchemaTypes.BUILDIN_TYPE || type == SchemaTypes.PROPERTY_CLASS) {
                classList.add(unwrapText(child.getText()));
            }
        }
        return classList;
    }

    public static String getName(SchemaPropertyMeta element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getValue(SchemaPropertyMeta element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.TEXT);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getPropertyName(SchemaSubPropertyInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.PROPERTY_NAME);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getName(SchemaSubPropertyMeta element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getValue(SchemaSubPropertyMeta element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.TEXT);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static String getPropertyAliasName(SchemaSubPropertyInfo element) {
        ASTNode valueNode = element.getNode().findChildByType(SchemaTypes.PROPERTY_ALIAS_NAME);
        return valueNode != null ? valueNode.getText() : null;
    }

    public static List<String> getPropertyClassList(SchemaSubPropertyInfo element) {
        List<String> classList = new ArrayList<>();
        for (PsiElement child : element.getChildren()) {
            IElementType type = child.getNode().getElementType();
            if (type == SchemaTypes.BUILDIN_TYPE || type == SchemaTypes.PROPERTY_CLASS) {
                classList.add(unwrapText(child.getText()));
            }
        }
        return classList;
    }

    public static String unwrapText(String text) {
        if (text == null) {
            return null;
        }

        if (text.startsWith("'") && text.endsWith("'")) {
            return text.substring(1, text.length() - 1);
        }

        if (text.startsWith("\"") && text.endsWith("\"")) {
            return text.substring(1, text.length() - 1);
        }

        return text;
    }

}
