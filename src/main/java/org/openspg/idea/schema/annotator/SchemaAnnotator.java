package org.openspg.idea.schema.annotator;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.schema.highlighter.SchemaSyntaxHighlighter;
import org.openspg.idea.lang.psi.SchemaEntity;
import org.openspg.idea.lang.psi.SchemaEntityInfo;

import java.util.Collection;

final class SchemaAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element.getNode().getElementType() == SchemaTypes.ENTITY_CLASS) {
            handleEntityType(element, holder);
        }
    }

    private void handleEntityType(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        String entityType = element.getText();

        SchemaEntity baseEntity = null;
        Collection<SchemaEntityInfo> infos = PsiTreeUtil.findChildrenOfType(element.getContainingFile(), SchemaEntityInfo.class);
        for (SchemaEntityInfo info : infos) {
            if (entityType.equals(info.getEntityName())) {
                baseEntity = PsiTreeUtil.getParentOfType(element, SchemaEntity.class);
            }
        }

        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());
        if (baseEntity == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved schema")
                    .range(keyRange)
                    .highlightType(ProblemHighlightType.LIKE_UNKNOWN_SYMBOL)
                    //.withFix(new SchemaCreatePropertyQuickFix(entityType))
                    .create();
        } else {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(keyRange)
                    .textAttributes(SchemaSyntaxHighlighter.ENTITY_TYPE)
                    .create();
        }
    }

}
