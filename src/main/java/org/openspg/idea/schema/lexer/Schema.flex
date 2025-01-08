package org.openspg.idea.lang.lexer;

import com.intellij.psi.tree.IElementType;

/* Auto generated File */
%%

// Language specification could be found here: http://www.yaml.org/spec/1.2/spec.html

%class SchemaLexer
%implements com.intellij.lexer.FlexLexer, org.openspg.idea.grammar.psi.SchemaTypes
%unicode
%public
%column

%function advance
%type IElementType

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////// USER CODE //////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

%{
    /**
     * The number of open but not closed braces.
     * Note: lexer does not distinguish braces from brackets while counting them.
     */
    private int myBraceCount = 0;

    private final int[] indentPos = {0, 0, 0, 0, 0, 0};
    private final int[] indentState = {ENTITY_STATE, META_STATE, ATTR_STATE, ATTRMETA_STATE, SUBPROP_STATE, SUBPROPMETA_STATE};
    private final int maxIndentLevel = 6;
    private int currentIndentLevel = 0;

    //-------------------------------------------------------------------------------------------------------------------

    /** @param offset offset from currently matched token start (could be negative) */
    private char getCharAtOffset(final int offset) {
        final int loc = getTokenStart() + offset;
        return 0 <= loc && loc < zzBuffer.length() ? zzBuffer.charAt(loc) : (char) -1;
    }

    private boolean isAfterEol() {
        final char prev = getCharAtOffset(-1);
        return prev == (char)-1 || prev == '\n';
    }

    private int getIndentState() {
        assert isAfterEol();
        int currentIndentPos = yylength();
        for (int i = 0; i < yylength(); i+=1) {
            if (getCharAtOffset(i) == '\t') {
                currentIndentPos += 1;
            }
        }

        int lastIndentPos = this.indentPos[this.currentIndentLevel];
        //trace("=================");
        //System.out.println("lastIndentPos: " + lastIndentPos);
        //System.out.println("currentIndentPos: " + currentIndentPos);
        if (currentIndentPos > lastIndentPos) {
            if (this.currentIndentLevel == maxIndentLevel) {
                return ERROR_STATE;
            }
            this.currentIndentLevel ++;
            this.indentPos[this.currentIndentLevel] = currentIndentPos;
            return this.indentState[this.currentIndentLevel];

        } else if (currentIndentPos < lastIndentPos) {
            for (int i = 0; i < this.currentIndentLevel; i++) {
                if (this.indentPos[i] == currentIndentPos) {
                    this.currentIndentLevel = i;
                    return this.indentState[this.currentIndentLevel];
                }
            }
            return ERROR_STATE;
        }
        return this.indentState[this.currentIndentLevel];
    }

    //-------------------------------------------------------------------------------------------------------------------
    private void openBrace() {
        myBraceCount++;
        if (myBraceCount != 0) {
            yybegin(PLAIN_BLOCK_STATE);
        }
    }

    private void closeBrace() {
        if (myBraceCount > 0) {
            myBraceCount--;
        }
        if (myBraceCount == 0){
            yybegin(this.indentState[this.currentIndentLevel]);
        }
    }

    private void goToState(int state) {
        yybegin(state);
        yypushback(yylength());
    }

    //-------------------------------------------------------------------------------------------------------------------
    private void trace(String tag) {
        int tokenStart = getTokenStart();
        int tokenEnd = Math.min(tokenStart + 40, zzBuffer.length());
        System.out.println("====" + tag + "\n{{ " + zzBuffer.subSequence(tokenStart, tokenEnd) + " }}");
    }
%}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////// REGEXPS DECLARATIONS //////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// NB !(!a|b) is "a - b"
// From the spec
ANY_CHAR = [^]

NS_CHAR = [^\n\t\r\ ]
NS_INDICATOR = [-?:,\(\)\[\]\{\}#&*!|>'\"%@`]

EOL =                           "\n"
WHITE_SPACE_CHAR =              [ \t]
WHITE_SPACE =                   {WHITE_SPACE_CHAR}+

NAME =                          [\w]+

LINE =                          [^\n]*

// Schema spec: when a comment follows another syntax element,
//  it must be separated from it by space characters.
// See http://www.yaml.org/spec/1.2/spec.html#comment
COMMENT =                       "#"{LINE}

ESCAPE_SEQUENCE=                \\[^\n]
DSTRING =                       \"([^\\\"]|{ESCAPE_SEQUENCE}|\\\n)*\"
STRING =                        '([^']|'')*'

TEXT =                          {DSTRING}|{STRING}|{NAME}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////// STATES DECLARATIONS //////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Main states
%xstate LINE_START_STATE, BLOCK_STATE, PLAIN_BLOCK_STATE

// Small technical one-token states
%xstate NAMESPACE_STATE, ENTITY_STATE, META_STATE, ATTR_STATE, ATTRMETA_STATE, SUBPROP_STATE, SUBPROPMETA_STATE, ERROR_STATE

%xstate WAITING_ENTITY_ALIAS_NAME_STATE, WAITING_ENTITY_CLASS_STATE
%xstate WAITING_METAINFO_VALUE_STATE
%xstate WAITING_ATTR_ALIAS_NAME_STATE, WAITING_ATTR_TYPE_STATE
%xstate WAITING_SUBPROP_TYPE_STATE

%%
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////// RULES declarations ////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// State in the start of new line in block mode
<YYINITIAL, LINE_START_STATE> {
    // It is a text, go next state and process it there
    "namespace" {
          this.currentIndentLevel = 0;
          goToState(NAMESPACE_STATE);
      }

    {WHITE_SPACE} {
          yybegin(getIndentState());
          return INDENT;
      }

    {ANY_CHAR} {
          this.currentIndentLevel = 0;
          goToState(ENTITY_STATE);
      }
}

<NAMESPACE_STATE, ENTITY_STATE, ATTR_STATE, SUBPROP_STATE, META_STATE, ATTRMETA_STATE, SUBPROPMETA_STATE, ERROR_STATE> {
    {WHITE_SPACE}*{EOL} {
          yybegin(LINE_START_STATE);
          return EOL;
      }

    {COMMENT} {
          return COMMENT;
      }

    {WHITE_SPACE} {
          return WHITESPACE;
      }
}

// level-1 namespace
<NAMESPACE_STATE> {
    "namespace" {
        return NAMESPACE_MARKER;
    }

    {TEXT} {
          return NAMESPACE_VALUE;
      }
}


<ERROR_STATE> {
    {ANY_CHAR} {
          return BAD_CHAR;
      }
}

<ENTITY_STATE> {
    [a-zA-Z0-9\.]+ {
          return ENTITY_NAME;
      }

    "(" {TEXT} ")" {
          goToState(WAITING_ENTITY_ALIAS_NAME_STATE);
      }

    [^\n] {
          return BAD_CHAR;
      }
}

<WAITING_ENTITY_ALIAS_NAME_STATE, WAITING_ENTITY_CLASS_STATE, WAITING_ATTR_ALIAS_NAME_STATE, WAITING_ATTR_TYPE_STATE, WAITING_SUBPROP_TYPE_STATE> {
    {WHITE_SPACE}*{EOL} {
          yybegin(LINE_START_STATE);
          return EOL;
      }

    {WHITE_SPACE} {
          return WHITESPACE;
      }
}

<WAITING_ENTITY_ALIAS_NAME_STATE> {

    "(" {
          return OPEN_BRACKET;
      }

    ")" {
          yybegin(WAITING_ENTITY_CLASS_STATE);
          return CLOSE_BRACKET;
      }

    {TEXT} {
          return ENTITY_ALIAS_NAME;
      }
}

<WAITING_ENTITY_CLASS_STATE> {
    "EntityType" | "ConceptType" | "EventType" | "StandardType" | "BasicType" {
          return ENTITY_BUILDIN_CLASS;
      }

    "," {
          return COMMA;
      }

    ":" {
          return COLON;
      }

    "->" {
          return INHERITED;
      }

    [a-zA-Z0-9]+ {
          return ENTITY_CLASS;
      }

    [^\n] {
          return BAD_CHAR;
      }
}

// level-2 mata-info
<META_STATE, ATTRMETA_STATE, SUBPROPMETA_STATE> {
    "desc" | "properties" | "relations" | "hypernymPredicate" | "constraint" | "rule" | "index" {
          if (yystate() == ATTRMETA_STATE) {
              return ATTRMETA_TYPE;

          } else if (yystate() == SUBPROPMETA_STATE) {
              return SUBPROPMETA_TYPE;
          }
          return META_TYPE;
      }

    ":" {
          yybegin(WAITING_METAINFO_VALUE_STATE);
          return COLON;
      }

    [^\n] {
          return BAD_CHAR;
      }
}

<WAITING_METAINFO_VALUE_STATE> {
    {WHITE_SPACE}*{EOL} {
          yybegin(LINE_START_STATE);
          return EOL;
      }

    {COMMENT} {
          return COMMENT;
      }

    {WHITE_SPACE} {
          return WHITESPACE;
      }

    "[[" {
          yybegin(PLAIN_BLOCK_STATE);
          return OPEN_PLAIN_BLOCK;
      }

    {TEXT} {
          return TEXT;
      }

    [^\n] {
          return BAD_CHAR;
      }
}

<ATTR_STATE> {
    {TEXT} {
          yybegin(WAITING_ATTR_ALIAS_NAME_STATE);
          return ATTR_NAME;
      }

    [^\n] {
          return BAD_CHAR;
      }

}

<WAITING_ATTR_ALIAS_NAME_STATE> {

    "(" {
          return OPEN_BRACKET;
      }

    ")" {
          return CLOSE_BRACKET;
      }

    {TEXT} {
          return ATTR_ALIAS_NAME;
      }

    ":" {
          yybegin(WAITING_ATTR_TYPE_STATE);
          return COLON;
      }

    [^\n] {
          return BAD_CHAR;
      }
}

<WAITING_ATTR_TYPE_STATE> {
    "Text" | "Float" | "Integer" {
          return BUILDIN_TYPE;
      }

    {TEXT} {
          return ATTR_TYPE;
      }

    [^\n] {
          return BAD_CHAR;
      }
}

// level-3 sub properties
<SUBPROP_STATE> {
    {TEXT} {
          return SUBPROP_NAME;
      }

    ":" {
          yybegin(WAITING_SUBPROP_TYPE_STATE);
          return COLON;
      }
}

<WAITING_SUBPROP_TYPE_STATE> {
    "Text" | "Float" | "Integer" {
          return BUILDIN_TYPE;
      }

    {TEXT} {
          return SUBPROP_TYPE;
      }

    [^\n] {
          return BAD_CHAR;
      }
}

<PLAIN_BLOCK_STATE> {
    {WHITE_SPACE}*{EOL} {
          return EOL;
      }

    {WHITE_SPACE} {
          return WHITESPACE;
      }

    "]]" {
          closeBrace();
          return CLOSE_PLAIN_BLOCK;
      }

    "]" {
          return TEXT;
      }

    [^\n\]]+ {
          return TEXT;
      }
}
