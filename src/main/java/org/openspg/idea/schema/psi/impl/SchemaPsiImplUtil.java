package org.openspg.idea.schema.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.lang.psi.SchemaEntity;
import org.openspg.idea.lang.psi.SchemaEntityInfo;
import org.openspg.idea.lang.psi.SchemaEntityMeta;
import org.openspg.idea.lang.psi.SchemaNamespace;

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
        ////List<String> classList = new ArrayList<String>();
        ////
        ////Collection<PsiElement> elements = PsiTreeUtil.findChildOfAnyType(element, SchemaTypes.ENTITY_BUILDIN_CLASS);
        ////if (valueNode != null) {
        ////    classList.add(unwrapText(valueNode.getText()));
        ////}
        ////
        ////valueNode = element.getNode().findChildByType(SchemaTypes.ENTITY_CLASS);
        ////if (valueNode != null) {
        ////    classList.add(unwrapText(valueNode.getText()));
        ////}
        //return classList;
        //TODO
        return null;
    }

    public static String getName(SchemaEntityInfo element) {
        return getEntityName(element);
    }

    public static PsiElement setName(SchemaEntityInfo element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        if (keyNode != null) {
            System.out.println("entity name: " + element.getNode().getElementType());
            //SimpleProperty property =
            //        SimpleElementFactory.createProperty(element.getProject(), newName);
            //ASTNode newKeyNode = property.getFirstChild().getNode();
            //element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SchemaEntityInfo element) {
        ASTNode keyNode = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    public static String getName(SchemaEntityMeta element) {
        ASTNode keyNode = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return keyNode != null ? keyNode.getText() : null;
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
