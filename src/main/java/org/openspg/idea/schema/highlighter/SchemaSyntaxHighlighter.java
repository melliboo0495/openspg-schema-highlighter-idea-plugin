package org.openspg.idea.schema.highlighter;


import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.schema.lexer.SchemaLexerAdapter;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class SchemaSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("SCHEMA_KEYWORDS", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("SCHEMA_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey ERROR =
            createTextAttributesKey("SCHEMA_ERROR", HighlighterColors.BAD_CHARACTER);

    public static final TextAttributesKey OPERATION_SIGN =
            createTextAttributesKey("SCHEMA_OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey ENTITY_NAME =
            createTextAttributesKey("SCHEMA_ENTITY_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey ENTITY_ALIAS =
            createTextAttributesKey("SCHEMA_ENTITY_ALIAS_NAME", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey ENTITY_REFERENCE =
            createTextAttributesKey("SCHEMA_ENTITY_REFERENCE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);


    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] ERROR_KEYS = new TextAttributesKey[]{ERROR};
    private static final TextAttributesKey[] OPERATION_SIGN_KEYS = new TextAttributesKey[]{OPERATION_SIGN};

    private static final TextAttributesKey[] ENTITY_NAME_KEYS = new TextAttributesKey[]{ENTITY_NAME};
    private static final TextAttributesKey[] ENTITY_ALIAS_KEYS = new TextAttributesKey[]{ENTITY_ALIAS};
    private static final TextAttributesKey[] ENTITY_REFERENCE_KEYS = new TextAttributesKey[]{ENTITY_REFERENCE};

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SchemaLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(SchemaTypes.NAMESPACE_MARKER)
                || tokenType.equals(SchemaTypes.ENTITY_BUILTIN_CLASS)
                || tokenType.equals(SchemaTypes.BUILTIN_TYPE)
                || tokenType.equals(SchemaTypes.META_TYPE)
        ) {
            return KEYWORD_KEYS;
        }

        if (tokenType.equals(SchemaTypes.COMMENT) || tokenType.equals(SchemaTypes.LINE_COMMENT)) {
            return COMMENT_KEYS;
        }

        if (tokenType.equals(SchemaTypes.COLON)
                || tokenType.equals(SchemaTypes.INHERITED)
                || tokenType.equals(SchemaTypes.COMMA)
                || tokenType.equals(SchemaTypes.OPEN_BRACKET)
                || tokenType.equals(SchemaTypes.CLOSE_BRACKET)
                || tokenType.equals(SchemaTypes.OPEN_PLAIN_BLOCK)
                || tokenType.equals(SchemaTypes.CLOSE_PLAIN_BLOCK)
        ) {
            return OPERATION_SIGN_KEYS;
        }

        if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return ERROR_KEYS;
        }

        if (tokenType.equals(SchemaTypes.ENTITY_NAME)) {
            return ENTITY_NAME_KEYS;
        }

        if (tokenType.equals(SchemaTypes.ENTITY_ALIAS_NAME)
                || tokenType.equals(SchemaTypes.PROPERTY_ALIAS_NAME)
        ) {
            return ENTITY_ALIAS_KEYS;
        }

        if (tokenType.equals(SchemaTypes.PROPERTY_CLASS)
                || tokenType.equals(SchemaTypes.ENTITY_CLASS)
        ) {
            return ENTITY_REFERENCE_KEYS;
        }

        return EMPTY_KEYS;
    }

}
