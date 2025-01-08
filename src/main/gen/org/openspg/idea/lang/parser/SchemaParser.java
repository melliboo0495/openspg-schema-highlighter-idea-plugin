// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.openspg.idea.grammar.psi.SchemaTypes.*;
import static org.openspg.idea.schema.grammar.SchemaParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SchemaParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return schemaFile(b, l + 1);
  }

  /* ********************************************************** */
  // (WHITESPACE | COMMENT | INDENT)* EOL
  static boolean EOL_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EOL_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = EOL__0(b, l + 1);
    r = r && consumeToken(b, EOL);
    exit_section_(b, m, null, r);
    return r;
  }

  // (WHITESPACE | COMMENT | INDENT)*
  private static boolean EOL__0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EOL__0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL__0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "EOL__0", c)) break;
    }
    return true;
  }

  // WHITESPACE | COMMENT | INDENT
  private static boolean EOL__0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EOL__0_0")) return false;
    boolean r;
    r = consumeToken(b, WHITESPACE);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, INDENT);
    return r;
  }

  /* ********************************************************** */
  // attribute_info EOL_* (INDENT attribute_meta EOL_*)*
  public static boolean attribute(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute")) return false;
    if (!nextTokenIs(b, ATTR_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute_info(b, l + 1);
    r = r && attribute_1(b, l + 1);
    r = r && attribute_2(b, l + 1);
    exit_section_(b, m, ATTRIBUTE, r);
    return r;
  }

  // EOL_*
  private static boolean attribute_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_1", c)) break;
    }
    return true;
  }

  // (INDENT attribute_meta EOL_*)*
  private static boolean attribute_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_2", c)) break;
    }
    return true;
  }

  // INDENT attribute_meta EOL_*
  private static boolean attribute_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && attribute_meta(b, l + 1);
    r = r && attribute_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL_*
  private static boolean attribute_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_2_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_2_0_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ATTR_NAME WHITESPACE* OPEN_BRACKET WHITESPACE* ATTR_ALIAS_NAME WHITESPACE* CLOSE_BRACKET WHITESPACE* COLON WHITESPACE* (BUILDIN_TYPE | ATTR_TYPE)
  public static boolean attribute_info(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info")) return false;
    if (!nextTokenIs(b, ATTR_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ATTR_NAME);
    r = r && attribute_info_1(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACKET);
    r = r && attribute_info_3(b, l + 1);
    r = r && consumeToken(b, ATTR_ALIAS_NAME);
    r = r && attribute_info_5(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET);
    r = r && attribute_info_7(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && attribute_info_9(b, l + 1);
    r = r && attribute_info_10(b, l + 1);
    exit_section_(b, m, ATTRIBUTE_INFO, r);
    return r;
  }

  // WHITESPACE*
  private static boolean attribute_info_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_info_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean attribute_info_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_info_3", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean attribute_info_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_info_5", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean attribute_info_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_info_7", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean attribute_info_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info_9")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_info_9", c)) break;
    }
    return true;
  }

  // BUILDIN_TYPE | ATTR_TYPE
  private static boolean attribute_info_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_info_10")) return false;
    boolean r;
    r = consumeToken(b, BUILDIN_TYPE);
    if (!r) r = consumeToken(b, ATTR_TYPE);
    return r;
  }

  /* ********************************************************** */
  // (ATTRMETA_TYPE WHITESPACE* COLON WHITESPACE* text_value*) EOL_* (INDENT sub_property)*
  public static boolean attribute_meta(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta")) return false;
    if (!nextTokenIs(b, ATTRMETA_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = attribute_meta_0(b, l + 1);
    r = r && attribute_meta_1(b, l + 1);
    r = r && attribute_meta_2(b, l + 1);
    exit_section_(b, m, ATTRIBUTE_META, r);
    return r;
  }

  // ATTRMETA_TYPE WHITESPACE* COLON WHITESPACE* text_value*
  private static boolean attribute_meta_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ATTRMETA_TYPE);
    r = r && attribute_meta_0_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && attribute_meta_0_3(b, l + 1);
    r = r && attribute_meta_0_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE*
  private static boolean attribute_meta_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_meta_0_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean attribute_meta_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "attribute_meta_0_3", c)) break;
    }
    return true;
  }

  // text_value*
  private static boolean attribute_meta_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_0_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!text_value(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_meta_0_4", c)) break;
    }
    return true;
  }

  // EOL_*
  private static boolean attribute_meta_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_meta_1", c)) break;
    }
    return true;
  }

  // (INDENT sub_property)*
  private static boolean attribute_meta_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!attribute_meta_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "attribute_meta_2", c)) break;
    }
    return true;
  }

  // INDENT sub_property
  private static boolean attribute_meta_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "attribute_meta_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && sub_property(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // COLON WHITESPACE* ENTITY_BUILDIN_CLASS
  static boolean basedEntityClass_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "basedEntityClass_")) return false;
    if (!nextTokenIs(b, COLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COLON);
    r = r && basedEntityClass__1(b, l + 1);
    r = r && consumeToken(b, ENTITY_BUILDIN_CLASS);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE*
  private static boolean basedEntityClass__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "basedEntityClass__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "basedEntityClass__1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // entity_info EOL_* (INDENT entity_meta)*
  public static boolean entity(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity")) return false;
    if (!nextTokenIs(b, ENTITY_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entity_info(b, l + 1);
    r = r && entity_1(b, l + 1);
    r = r && entity_2(b, l + 1);
    exit_section_(b, m, ENTITY, r);
    return r;
  }

  // EOL_*
  private static boolean entity_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entity_1", c)) break;
    }
    return true;
  }

  // (INDENT entity_meta)*
  private static boolean entity_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!entity_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entity_2", c)) break;
    }
    return true;
  }

  // INDENT entity_meta
  private static boolean entity_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && entity_meta(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ENTITY_NAME WHITESPACE* OPEN_BRACKET WHITESPACE* ENTITY_ALIAS_NAME WHITESPACE* CLOSE_BRACKET WHITESPACE* (basedEntityClass_|inheritedEntityClass_)
  public static boolean entity_info(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_info")) return false;
    if (!nextTokenIs(b, ENTITY_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ENTITY_NAME);
    r = r && entity_info_1(b, l + 1);
    r = r && consumeToken(b, OPEN_BRACKET);
    r = r && entity_info_3(b, l + 1);
    r = r && consumeToken(b, ENTITY_ALIAS_NAME);
    r = r && entity_info_5(b, l + 1);
    r = r && consumeToken(b, CLOSE_BRACKET);
    r = r && entity_info_7(b, l + 1);
    r = r && entity_info_8(b, l + 1);
    exit_section_(b, m, ENTITY_INFO, r);
    return r;
  }

  // WHITESPACE*
  private static boolean entity_info_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_info_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "entity_info_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean entity_info_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_info_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "entity_info_3", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean entity_info_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_info_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "entity_info_5", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean entity_info_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_info_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "entity_info_7", c)) break;
    }
    return true;
  }

  // basedEntityClass_|inheritedEntityClass_
  private static boolean entity_info_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_info_8")) return false;
    boolean r;
    r = basedEntityClass_(b, l + 1);
    if (!r) r = inheritedEntityClass_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // (META_TYPE WHITESPACE* COLON WHITESPACE* text_value*) EOL_* (INDENT attribute)*
  public static boolean entity_meta(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta")) return false;
    if (!nextTokenIs(b, META_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = entity_meta_0(b, l + 1);
    r = r && entity_meta_1(b, l + 1);
    r = r && entity_meta_2(b, l + 1);
    exit_section_(b, m, ENTITY_META, r);
    return r;
  }

  // META_TYPE WHITESPACE* COLON WHITESPACE* text_value*
  private static boolean entity_meta_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, META_TYPE);
    r = r && entity_meta_0_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && entity_meta_0_3(b, l + 1);
    r = r && entity_meta_0_4(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE*
  private static boolean entity_meta_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "entity_meta_0_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean entity_meta_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "entity_meta_0_3", c)) break;
    }
    return true;
  }

  // text_value*
  private static boolean entity_meta_0_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_0_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!text_value(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entity_meta_0_4", c)) break;
    }
    return true;
  }

  // EOL_*
  private static boolean entity_meta_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entity_meta_1", c)) break;
    }
    return true;
  }

  // (INDENT attribute)*
  private static boolean entity_meta_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!entity_meta_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "entity_meta_2", c)) break;
    }
    return true;
  }

  // INDENT attribute
  private static boolean entity_meta_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "entity_meta_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && attribute(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // INHERITED WHITESPACE* ((ENTITY_CLASS WHITESPACE* COMMA WHITESPACE*)* ENTITY_CLASS) WHITESPACE* COLON
  static boolean inheritedEntityClass_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass_")) return false;
    if (!nextTokenIs(b, INHERITED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INHERITED);
    r = r && inheritedEntityClass__1(b, l + 1);
    r = r && inheritedEntityClass__2(b, l + 1);
    r = r && inheritedEntityClass__3(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE*
  private static boolean inheritedEntityClass__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "inheritedEntityClass__1", c)) break;
    }
    return true;
  }

  // (ENTITY_CLASS WHITESPACE* COMMA WHITESPACE*)* ENTITY_CLASS
  private static boolean inheritedEntityClass__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = inheritedEntityClass__2_0(b, l + 1);
    r = r && consumeToken(b, ENTITY_CLASS);
    exit_section_(b, m, null, r);
    return r;
  }

  // (ENTITY_CLASS WHITESPACE* COMMA WHITESPACE*)*
  private static boolean inheritedEntityClass__2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__2_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!inheritedEntityClass__2_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "inheritedEntityClass__2_0", c)) break;
    }
    return true;
  }

  // ENTITY_CLASS WHITESPACE* COMMA WHITESPACE*
  private static boolean inheritedEntityClass__2_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__2_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ENTITY_CLASS);
    r = r && inheritedEntityClass__2_0_0_1(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && inheritedEntityClass__2_0_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // WHITESPACE*
  private static boolean inheritedEntityClass__2_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__2_0_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "inheritedEntityClass__2_0_0_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean inheritedEntityClass__2_0_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__2_0_0_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "inheritedEntityClass__2_0_0_3", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean inheritedEntityClass__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "inheritedEntityClass__3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "inheritedEntityClass__3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // namespace|entity|DOCUMENT_END|COMMENT|BAD_CHAR|INDENT|WHITESPACE|EOL|TEXT
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = namespace(b, l + 1);
    if (!r) r = entity(b, l + 1);
    if (!r) r = consumeToken(b, DOCUMENT_END);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, BAD_CHAR);
    if (!r) r = consumeToken(b, INDENT);
    if (!r) r = consumeToken(b, WHITESPACE);
    if (!r) r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, TEXT);
    return r;
  }

  /* ********************************************************** */
  // NAMESPACE_MARKER WHITESPACE+ NAMESPACE_VALUE
  public static boolean namespace(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespace")) return false;
    if (!nextTokenIs(b, NAMESPACE_MARKER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NAMESPACE_MARKER);
    r = r && namespace_1(b, l + 1);
    r = r && consumeToken(b, NAMESPACE_VALUE);
    exit_section_(b, m, NAMESPACE, r);
    return r;
  }

  // WHITESPACE+
  private static boolean namespace_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "namespace_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHITESPACE);
    while (r) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "namespace_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // OPEN_PLAIN_BLOCK (TEXT|EOL|WHITESPACE)* CLOSE_PLAIN_BLOCK
  static boolean plain_text_block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plain_text_block")) return false;
    if (!nextTokenIs(b, OPEN_PLAIN_BLOCK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN_PLAIN_BLOCK);
    r = r && plain_text_block_1(b, l + 1);
    r = r && consumeToken(b, CLOSE_PLAIN_BLOCK);
    exit_section_(b, m, null, r);
    return r;
  }

  // (TEXT|EOL|WHITESPACE)*
  private static boolean plain_text_block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plain_text_block_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!plain_text_block_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "plain_text_block_1", c)) break;
    }
    return true;
  }

  // TEXT|EOL|WHITESPACE
  private static boolean plain_text_block_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plain_text_block_1_0")) return false;
    boolean r;
    r = consumeToken(b, TEXT);
    if (!r) r = consumeToken(b, EOL);
    if (!r) r = consumeToken(b, WHITESPACE);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean schemaFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "schemaFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "schemaFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // sub_property_info EOL_* (INDENT sub_property_meta EOL_*)*
  public static boolean sub_property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property")) return false;
    if (!nextTokenIs(b, SUBPROP_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = sub_property_info(b, l + 1);
    r = r && sub_property_1(b, l + 1);
    r = r && sub_property_2(b, l + 1);
    exit_section_(b, m, SUB_PROPERTY, r);
    return r;
  }

  // EOL_*
  private static boolean sub_property_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_1", c)) break;
    }
    return true;
  }

  // (INDENT sub_property_meta EOL_*)*
  private static boolean sub_property_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!sub_property_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_2", c)) break;
    }
    return true;
  }

  // INDENT sub_property_meta EOL_*
  private static boolean sub_property_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, INDENT);
    r = r && sub_property_meta(b, l + 1);
    r = r && sub_property_2_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // EOL_*
  private static boolean sub_property_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_2_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!EOL_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_2_0_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SUBPROP_NAME WHITESPACE* COLON WHITESPACE* (BUILDIN_TYPE | SUBPROP_TYPE)
  public static boolean sub_property_info(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_info")) return false;
    if (!nextTokenIs(b, SUBPROP_NAME)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUBPROP_NAME);
    r = r && sub_property_info_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && sub_property_info_3(b, l + 1);
    r = r && sub_property_info_4(b, l + 1);
    exit_section_(b, m, SUB_PROPERTY_INFO, r);
    return r;
  }

  // WHITESPACE*
  private static boolean sub_property_info_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_info_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_info_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean sub_property_info_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_info_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_info_3", c)) break;
    }
    return true;
  }

  // BUILDIN_TYPE | SUBPROP_TYPE
  private static boolean sub_property_info_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_info_4")) return false;
    boolean r;
    r = consumeToken(b, BUILDIN_TYPE);
    if (!r) r = consumeToken(b, SUBPROP_TYPE);
    return r;
  }

  /* ********************************************************** */
  // SUBPROPMETA_TYPE WHITESPACE* COLON WHITESPACE* text_value*
  public static boolean sub_property_meta(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_meta")) return false;
    if (!nextTokenIs(b, SUBPROPMETA_TYPE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SUBPROPMETA_TYPE);
    r = r && sub_property_meta_1(b, l + 1);
    r = r && consumeToken(b, COLON);
    r = r && sub_property_meta_3(b, l + 1);
    r = r && sub_property_meta_4(b, l + 1);
    exit_section_(b, m, SUB_PROPERTY_META, r);
    return r;
  }

  // WHITESPACE*
  private static boolean sub_property_meta_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_meta_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_meta_1", c)) break;
    }
    return true;
  }

  // WHITESPACE*
  private static boolean sub_property_meta_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_meta_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, WHITESPACE)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_meta_3", c)) break;
    }
    return true;
  }

  // text_value*
  private static boolean sub_property_meta_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sub_property_meta_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!text_value(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sub_property_meta_4", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // TEXT | plain_text_block
  static boolean text_value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_value")) return false;
    if (!nextTokenIs(b, "", OPEN_PLAIN_BLOCK, TEXT)) return false;
    boolean r;
    r = consumeToken(b, TEXT);
    if (!r) r = plain_text_block(b, l + 1);
    return r;
  }

}
