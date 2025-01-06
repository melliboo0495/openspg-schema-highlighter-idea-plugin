package org.openspg.idea.schema.grammar;

import com.intellij.lang.ASTNode;
import com.intellij.lang.LightPsiParser;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class MySchemaParser implements PsiParser, LightPsiParser {

    @Override
    public @NotNull ASTNode parse(@NotNull IElementType root, @NotNull PsiBuilder builder) {
        parseLight(root, builder);
        return builder.getTreeBuilt();
    }

    @Override
    public void parseLight(IElementType root, PsiBuilder builder) {
        final PsiBuilder.Marker fileMarker = builder.mark();

        while (!builder.eof()) {
            List<ElementToken> tokens = this.readLine(builder);
            for (ElementToken token : tokens) {
                System.out.print(token.tokenType + "<<" + token.tokenText.replace("\n", "\\n") + ">> ");
            }
            System.out.println();
        }

        fileMarker.done(root);
    }

    private List<ElementToken> readLine(PsiBuilder builder) {
        List<ElementToken> tags = new ArrayList<>();
        while (!builder.eof()) {
            IElementType tokenType = builder.getTokenType();
            if (tokenType == SchemaTypes.EOL) {
                builder.advanceLexer();
                break;
            }
            tags.add(new ElementToken(tokenType, builder.getTokenText()));
            builder.advanceLexer();
        }
        return tags;
    }

    public static class ElementToken {
        IElementType tokenType;
        String tokenText;

        public ElementToken(IElementType tokenType, String tokenText) {
            this.tokenType = tokenType;
            this.tokenText = tokenText;
        }
    }

}
