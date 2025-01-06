package org.openspg.idea.schema;

import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openspg.idea.schema.lexer.SchemaLexerAdapter;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.lang.psi.SchemaEntityInfo;

public class SchemaFindUsagesProvider implements FindUsagesProvider {

    TokenSet comments = TokenSet.create(
            SchemaTypes.COMMENT
    );

    TokenSet identifiers = TokenSet.create(
            SchemaTypes.ENTITY_NAME
    );

    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new SchemaLexerAdapter(), identifiers, comments, TokenSet.EMPTY);
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        if (psiElement instanceof SchemaEntityInfo) {
            //return psiElement.getNode().getElementType() == SchemaTypes.ENTITY_TYPE;
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof SchemaEntityInfo) {
            return "Schema Entity";
        }
        return "";
    }

    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof SchemaEntityInfo) {
            return ((SchemaEntityInfo) element).getEntityName();
        }
        return "";
    }

    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof SchemaEntityInfo info) {
            return info.getEntityName() + ":" + info.getEntityType();
        }
        return "";
    }
}
