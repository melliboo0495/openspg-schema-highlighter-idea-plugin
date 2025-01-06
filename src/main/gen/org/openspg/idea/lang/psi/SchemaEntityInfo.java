// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi;

import com.intellij.psi.PsiElement;
import org.openspg.idea.schema.psi.SchemaNamedElement;

public interface SchemaEntityInfo extends SchemaNamedElement {

  String getEntityName();

  String getEntityAliasName();

  String getEntityType();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
