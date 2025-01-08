package org.openspg.idea.schema.reference;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.lang.psi.SchemaEntityInfo;

public class SchemaReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(PlatformPatterns.psiElement(SchemaTypes.ENTITY_CLASS),
                new PsiReferenceProvider() {
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        if (!(element instanceof SchemaEntityInfo)) {
                            return PsiReference.EMPTY_ARRAY;
                        }
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        String value = literalExpression.getValue() instanceof String ?
                                (String) literalExpression.getValue() : null;
                        if ((value != null && value.startsWith("Schema:"))) {
                            TextRange property = new TextRange(1, value.length() + 1);
                            return new PsiReference[]{new SchemaReference(element, property)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }

}
