package org.openspg.idea.schema.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.lang.psi.*;
import org.openspg.idea.schema.resolve.SchemaEntityClassReference;
import org.openspg.idea.schema.resolve.SchemaEntityNameReference;
import org.openspg.idea.schema.resolve.SchemaPropertyClassReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SchemaPsiImplUtil {

    public static List<PsiElement> searchChildrenOfAnyType(@NotNull PsiElement psiRoot, boolean recursion, IElementType @NotNull ... types) {
        List<PsiElement> results = new ArrayList<>();
        searchChildrenOfAnyType(results, psiRoot, recursion, types);
        return results;
    }

    private static void searchChildrenOfAnyType(@NotNull List<PsiElement> result, @NotNull PsiElement psiRoot, boolean recursion, IElementType @NotNull ... types) {
        PsiElement psiChild = psiRoot.getFirstChild();
        while (psiChild != null) {
            IElementType childType = psiChild.getNode().getElementType();
            for (IElementType type : types) {
                if (childType.equals(type)) {
                    result.add(psiChild);
                    break;
                }
            }

            if (recursion) {
                searchChildrenOfAnyType(result, psiChild, recursion, types);
            }
            psiChild = psiChild.getNextSibling();
        }
    }

    public static SchemaEntity findEntityByName(@NotNull PsiFile file, @NotNull String entityName) {
        Collection<SchemaEntity> entities = PsiTreeUtil.findChildrenOfType(file, SchemaEntity.class);
        for (SchemaEntity entity : entities) {
            SchemaEntityInfo info = entity.getEntityInfo();
            if (entityName.equals(info.getEntityName())) {
                return entity;
            }
        }
        return null;
    }

    public static String getValue(SchemaNamespace element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.TEXT);
        if (node != null) {
            return node.getText();
        } else {
            return null;
        }
    }

    public static String getName(SchemaEntity element) {
        return element.getEntityInfo().getEntityName();
    }

    public static String getEntityName(SchemaEntityInfo element) {
        ASTNode node = element.getReferencableEntityName().getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        if (node != null) {
            return unwrapText(node.getText());
        } else {
            return null;
        }
    }

    public static String getEntityAliasName(SchemaEntityInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.ENTITY_ALIAS_NAME);
        if (node != null) {
            return unwrapText(node.getText());
        } else {
            return null;
        }
    }

    public static List<SchemaReferencableEntityClass> getEntityClassList(SchemaEntityInfo element) {
        return PsiTreeUtil.getChildrenOfTypeAsList(element, SchemaReferencableEntityClass.class);
    }

    public static String getName(SchemaEntityInfo element) {
        return getEntityName(element);
    }

    public static PsiElement setName(SchemaEntityInfo element, String newName) {
        throw new IllegalArgumentException("unsupported operation. setName(SchemaEntityInfo element, String newName)");
        //System.out.println("TODO: SchemaPsiImplUtil.setName()");
        //ASTNode node = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        //if (node != null) {
        //    System.out.println("TODO: SchemaPsiImplUtil.setName(SchemaEntityInfo element, String newName)");
        //    //element.getNode().replaceChild(node, newKeyNode);
        //}
        //return element;
    }

    public static PsiElement getNameIdentifier(SchemaEntityInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.ENTITY_NAME);
        return node != null ? node.getPsi(): null;
    }

    public static PsiReference getReference(SchemaReferencableEntityName element) {
        return new SchemaEntityNameReference(element);
    }

    public static PsiReference getReference(SchemaReferencableEntityClass element) {
        return new SchemaEntityClassReference(element);
    }

    public static String getName(SchemaEntityMetaInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return node != null ? node.getText(): null;
    }

    public static String getValue(SchemaEntityMetaInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.TEXT);
        return node != null ? node.getText(): null;
    }

    public static String getPropertyName(SchemaPropertyInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.PROPERTY_NAME);
        return node != null ? node.getText(): null;
    }

    public static String getPropertyAliasName(SchemaPropertyInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.PROPERTY_ALIAS_NAME);
        return node != null ? node.getText(): null;
    }

    public static List<SchemaReferencablePropertyClass> getPropertyClassList(SchemaPropertyInfo element) {
        return PsiTreeUtil.getChildrenOfTypeAsList(element, SchemaReferencablePropertyClass.class);
    }

    public static PsiReference getReference(SchemaReferencablePropertyClass element) {
        return new SchemaPropertyClassReference(element);
    }

    public static String getName(SchemaPropertyMeta element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return node != null ? node.getText(): null;
    }

    public static String getValue(SchemaPropertyMeta element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.TEXT);
        return node != null ? node.getText(): null;
    }

    public static String getPropertyName(SchemaSubPropertyInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.PROPERTY_NAME);
        return node != null ? node.getText(): null;
    }

    public static String getName(SchemaSubPropertyMeta element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.META_TYPE);
        return node != null ? node.getText(): null;
    }

    public static String getValue(SchemaSubPropertyMeta element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.TEXT);
        return node != null ? node.getText(): null;
    }

    public static String getPropertyAliasName(SchemaSubPropertyInfo element) {
        ASTNode node = element.getNode().findChildByType(SchemaTypes.PROPERTY_ALIAS_NAME);
        return node != null ? node.getText(): null;
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
