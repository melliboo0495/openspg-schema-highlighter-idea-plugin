package org.openspg.idea.schema.highlighter;


import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.grammar.psi.SchemaTypes;
import org.openspg.idea.schema.lexer.SchemaLexerAdapter;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;


public class SchemaSyntaxHighlighter extends SyntaxHighlighterBase {

    public static final TextAttributesKey KEYWORDS =
            createTextAttributesKey("SCHEMA_KEYWORDS", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("SCHEMA_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);

    public static final TextAttributesKey CLASS_NAME =
            createTextAttributesKey("SCHEMA_CLASS_NAME", DefaultLanguageHighlighterColors.CLASS_NAME);

    public static final TextAttributesKey ALIAS_NAME =
            createTextAttributesKey("SCHEMA_ALIAS_NAME", DefaultLanguageHighlighterColors.STATIC_FIELD);

    public static final TextAttributesKey ENTITY_TYPE =
            createTextAttributesKey("SCHEMA_ENTITY_TYPE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);


    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("SCHEMA_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


    private static final TextAttributesKey[] KEYWORDS_KEYS = new TextAttributesKey[]{KEYWORDS};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};

    private static final TextAttributesKey[] CLASS_NAME_KEYS = new TextAttributesKey[]{CLASS_NAME};
    private static final TextAttributesKey[] ALIAS_NAME_KEYS = new TextAttributesKey[]{ALIAS_NAME};

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new SchemaLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        //System.out.println("SchemaSyntaxHighlighter.getTokenHighlights " + tokenType);

        if (tokenType.equals(SchemaTypes.NAMESPACE_MARKER)
                || tokenType.equals(SchemaTypes.ENTITY_BUILDIN_TYPE)
                || tokenType.equals(SchemaTypes.BUILDIN_TYPE)
                || tokenType.equals(SchemaTypes.META_TYPE)
                || tokenType.equals(SchemaTypes.ATTRMETA_TYPE)
                || tokenType.equals(SchemaTypes.SUBPROPMETA_TYPE)
        ) {
            return KEYWORDS_KEYS;
        }

        if (tokenType.equals(SchemaTypes.COMMENT)) {
            return COMMENT_KEYS;
        }

        if (tokenType.equals(SchemaTypes.BAD_CHAR)) {
            return BAD_CHAR_KEYS;
        }

        if (tokenType.equals(SchemaTypes.ENTITY_NAME)
                || tokenType.equals(SchemaTypes.ENTITY_TYPE)
        ) {
            return CLASS_NAME_KEYS;
        }

        if (tokenType.equals(SchemaTypes.ENTITY_ALIAS_NAME)
                || tokenType.equals(SchemaTypes.ATTR_ALIAS_NAME)
        ) {
            return ALIAS_NAME_KEYS;
        }

        return EMPTY_KEYS;
    }

}
