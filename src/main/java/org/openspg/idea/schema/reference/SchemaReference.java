package org.openspg.idea.schema.reference;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReferenceBase;
import com.intellij.psi.ResolveResult;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SchemaReference extends PsiPolyVariantReferenceBase<PsiElement> {

    private final String key;

    SchemaReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText()
                .substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        //List<SimpleProperty> properties = SimpleUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        //for (SimpleProperty property : properties) {
        //    results.add(new PsiElementResolveResult(property));
        //}
        return results.toArray(new ResolveResult[0]);
    }

    @Override
    public Object @NotNull [] getVariants() {
        List<LookupElement> variants = new ArrayList<>();
        Project project = myElement.getProject();
        //List<SimpleProperty> properties = SimpleUtil.findProperties(project);
        //for (SimpleProperty property : properties) {
        //    if (property.getKey() != null && !property.getKey().isEmpty()) {
        //        variants.add(LookupElementBuilder
        //                .create(property).withIcon(SimpleIcons.FILE)
        //                .withTypeText(property.getContainingFile().getName())
        //        );
        //    }
        //}
        return variants.toArray();
    }
}
