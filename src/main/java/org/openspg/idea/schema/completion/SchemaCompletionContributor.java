package org.openspg.idea.schema.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.lang.psi.SchemaEntityInfo;

import java.util.Collection;

final class SchemaCompletionContributor extends CompletionContributor {

    SchemaCompletionContributor() {
        // extend EntityType (level 1) completion
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SchemaTypes.ENTITY_TYPE),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("EntityType"));
                        resultSet.addElement(LookupElementBuilder.create("ConceptType"));
                        resultSet.addElement(LookupElementBuilder.create("EventType"));

                        SchemaEntityInfo currentInfo = (SchemaEntityInfo) PsiTreeUtil.findFirstParent(parameters.getPosition(), SchemaEntityInfo.class::isInstance);
                        String currentEntityName = currentInfo == null ? null : currentInfo.getEntityName();

                        Collection<SchemaEntityInfo> infos = PsiTreeUtil.findChildrenOfType(parameters.getOriginalFile(), SchemaEntityInfo.class);
                        for (SchemaEntityInfo info : infos) {
                            if (info.getEntityName() != null && !info.getEntityName().equals(currentEntityName)) {
                                resultSet.addElement(LookupElementBuilder.create(info.getEntityName()));
                            }
                        }
                    }
                }
        );

        // extend AttrType/SubPropType completion
        CompletionProvider<CompletionParameters> attrTypeProvider = new CompletionProvider<>() {
            public void addCompletions(@NotNull CompletionParameters parameters,
                                       @NotNull ProcessingContext context,
                                       @NotNull CompletionResultSet resultSet) {
                resultSet.addElement(LookupElementBuilder.create("Text"));
                resultSet.addElement(LookupElementBuilder.create("Float"));
                resultSet.addElement(LookupElementBuilder.create("Integer"));
            }
        };
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SchemaTypes.ATTR_TYPE), attrTypeProvider);
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SchemaTypes.SUBPROP_TYPE), attrTypeProvider);

        // TODO: extend any elements completion
        //CompletionProvider<CompletionParameters> metaProvider = new CompletionProvider<>() {
        //    public void addCompletions(@NotNull CompletionParameters parameters,
        //                               @NotNull ProcessingContext context,
        //                               @NotNull CompletionResultSet resultSet) {
        //        resultSet.addElement(LookupElementBuilder.create("desc"));
        //        resultSet.addElement(LookupElementBuilder.create("properties"));
        //        resultSet.addElement(LookupElementBuilder.create("relations"));
        //        resultSet.addElement(LookupElementBuilder.create("hypernymPredicate"));
        //        resultSet.addElement(LookupElementBuilder.create("constraint"));
        //        resultSet.addElement(LookupElementBuilder.create("rule"));
        //    }
        //};
        //extend(CompletionType.BASIC, PlatformPatterns.psiElement(SchemaTypes.TEXT), metaProvider);
        //extend(CompletionType.BASIC, PlatformPatterns.psiElement(SchemaTypes.ATTRIBUTE_META), metaProvider);
        //extend(CompletionType.BASIC, PlatformPatterns.psiElement(SchemaTypes.SUB_PROPERTY_META), metaProvider);

    }

}
