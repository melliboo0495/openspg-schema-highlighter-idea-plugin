package org.openspg.idea.schema.formatter;

import com.intellij.formatting.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.schema.SchemaLanguage;

public final class SchemaFormattingModelBuilder implements FormattingModelBuilder {

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, SchemaLanguage.INSTANCE)
                .around(SchemaTypes.COLON)
                .spaceIf(settings.getCommonSettings(SchemaLanguage.INSTANCE.getID()).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(SchemaTypes.ENTITY_NAME)
                .none();
    }

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

}
