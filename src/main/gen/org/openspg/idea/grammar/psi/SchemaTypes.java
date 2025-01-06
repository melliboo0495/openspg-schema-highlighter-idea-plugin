// This is a generated file. Not intended for manual editing.
package org.openspg.idea.grammar.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.openspg.idea.schema.SchemaElementType;
import org.openspg.idea.schema.psi.SchemaTokenType;
import org.openspg.idea.lang.psi.impl.*;

public interface SchemaTypes {

  IElementType ATTRIBUTE = new SchemaElementType("ATTRIBUTE");
  IElementType ATTRIBUTE_INFO = new SchemaElementType("ATTRIBUTE_INFO");
  IElementType ATTRIBUTE_META = new SchemaElementType("ATTRIBUTE_META");
  IElementType ENTITY = new SchemaElementType("ENTITY");
  IElementType ENTITY_INFO = new SchemaElementType("ENTITY_INFO");
  IElementType ENTITY_META = new SchemaElementType("ENTITY_META");
  IElementType NAMESPACE = new SchemaElementType("NAMESPACE");
  IElementType SUB_PROPERTY = new SchemaElementType("SUB_PROPERTY");
  IElementType SUB_PROPERTY_INFO = new SchemaElementType("SUB_PROPERTY_INFO");
  IElementType SUB_PROPERTY_META = new SchemaElementType("SUB_PROPERTY_META");

  IElementType ATTRMETA_TYPE = new SchemaTokenType("ATTRMETA_TYPE");
  IElementType ATTR_ALIAS_NAME = new SchemaTokenType("ATTR_ALIAS_NAME");
  IElementType ATTR_NAME = new SchemaTokenType("ATTR_NAME");
  IElementType ATTR_TYPE = new SchemaTokenType("ATTR_TYPE");
  IElementType BAD_CHAR = new SchemaTokenType("BAD_CHAR");
  IElementType BUILDIN_TYPE = new SchemaTokenType("BUILDIN_TYPE");
  IElementType CLOSE_BRACKET = new SchemaTokenType("CLOSE_BRACKET");
  IElementType CLOSE_PLAIN_BLOCK = new SchemaTokenType("CLOSE_PLAIN_BLOCK");
  IElementType COLON = new SchemaTokenType("COLON");
  IElementType COMMENT = new SchemaTokenType("COMMENT");
  IElementType DOCUMENT_END = new SchemaTokenType("DOCUMENT_END");
  IElementType ENTITY_ALIAS_NAME = new SchemaTokenType("ENTITY_ALIAS_NAME");
  IElementType ENTITY_BUILDIN_TYPE = new SchemaTokenType("ENTITY_BUILDIN_TYPE");
  IElementType ENTITY_NAME = new SchemaTokenType("ENTITY_NAME");
  IElementType ENTITY_TYPE = new SchemaTokenType("ENTITY_TYPE");
  IElementType EOL = new SchemaTokenType("EOL");
  IElementType INDENT = new SchemaTokenType("INDENT");
  IElementType INHERITED = new SchemaTokenType("INHERITED");
  IElementType META_TYPE = new SchemaTokenType("META_TYPE");
  IElementType NAMESPACE_MARKER = new SchemaTokenType("NAMESPACE_MARKER");
  IElementType NAMESPACE_VALUE = new SchemaTokenType("NAMESPACE_VALUE");
  IElementType OPEN_BRACKET = new SchemaTokenType("OPEN_BRACKET");
  IElementType OPEN_PLAIN_BLOCK = new SchemaTokenType("OPEN_PLAIN_BLOCK");
  IElementType SUBPROPMETA_TYPE = new SchemaTokenType("SUBPROPMETA_TYPE");
  IElementType SUBPROP_NAME = new SchemaTokenType("SUBPROP_NAME");
  IElementType SUBPROP_TYPE = new SchemaTokenType("SUBPROP_TYPE");
  IElementType TEXT = new SchemaTokenType("TEXT");
  IElementType WHITESPACE = new SchemaTokenType("WHITESPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ATTRIBUTE) {
        return new SchemaAttributeImpl(node);
      }
      else if (type == ATTRIBUTE_INFO) {
        return new SchemaAttributeInfoImpl(node);
      }
      else if (type == ATTRIBUTE_META) {
        return new SchemaAttributeMetaImpl(node);
      }
      else if (type == ENTITY) {
        return new SchemaEntityImpl(node);
      }
      else if (type == ENTITY_INFO) {
        return new SchemaEntityInfoImpl(node);
      }
      else if (type == ENTITY_META) {
        return new SchemaEntityMetaImpl(node);
      }
      else if (type == NAMESPACE) {
        return new SchemaNamespaceImpl(node);
      }
      else if (type == SUB_PROPERTY) {
        return new SchemaSubPropertyImpl(node);
      }
      else if (type == SUB_PROPERTY_INFO) {
        return new SchemaSubPropertyInfoImpl(node);
      }
      else if (type == SUB_PROPERTY_META) {
        return new SchemaSubPropertyMetaImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
