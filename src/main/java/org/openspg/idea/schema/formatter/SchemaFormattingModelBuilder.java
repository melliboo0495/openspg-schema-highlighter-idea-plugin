package org.openspg.idea.schema.formatter;

import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.schema.SchemaLanguage;

public final class SchemaFormattingModelBuilder implements FormattingModelBuilder {

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider.createFormattingModelForPsiFile(
                formattingContext.getContainingFile(),
                new SchemaBlock(
                        formattingContext.getNode(),
                        Wrap.createWrap(WrapType.NONE, false),
                        Alignment.createAlignment(),
                        createSpaceBuilder(codeStyleSettings)
                ),
                codeStyleSettings
        );
    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        int indentSize = 4;
        return new SpacingBuilder(settings, SchemaLanguage.INSTANCE)

                .after(SchemaTypes.NAMESPACE_MARKER)
                .spaces(1)

                .after(TokenSet.create(SchemaTypes.NAMESPACE, SchemaTypes.ENTITY))
                .spacing(0, 0, 1, false, 2)

                .before(TokenSet.create(
                        SchemaTypes.ENTITY_INFO, SchemaTypes.ENTITY_META, SchemaTypes.ENTITY_META_INFO,
                        SchemaTypes.PROPERTY_INFO, SchemaTypes.PROPERTY_META_INFO,
                        SchemaTypes.SUB_PROPERTY_INFO, SchemaTypes.SUB_PROPERTY_META,
                        SchemaTypes.LINE_COMMENT
                ))
                .spacing(0, 0, 1, false, 0)

                .after(SchemaTypes.INDENT_META)
                .spaces(indentSize - 1)

                .after(SchemaTypes.INDENT_PROP)
                .spaces(indentSize * 2 - 1)

                .after(SchemaTypes.INDENT_PROPMETA)
                .spaces(indentSize * 3 - 1)

                .after(SchemaTypes.INDENT_SUBPROP)
                .spaces(indentSize * 4 - 1)

                .after(SchemaTypes.INDENT_SUBPROPMETA)
                .spaces(indentSize * 5 - 1)

                .before(SchemaTypes.OPEN_BRACKET)
                .spaces(1)

                .after(SchemaTypes.CLOSE_BRACKET)
                .spaces(1)

                .after(SchemaTypes.COLON)
                .spaces(1)

                .around(SchemaTypes.INHERITED)
                .spaces(1)

                //.spaceIf(settings.getCommonSettings(SchemaLanguage.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                //.before(SchemaTypes.ENTITY_NAME)
                ;
    }

}
