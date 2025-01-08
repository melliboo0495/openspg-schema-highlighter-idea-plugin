// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.openspg.idea.schema.psi.SchemaNamedElement;

public interface SchemaEntityInfo extends SchemaNamedElement {

  String getEntityName();

  String getEntityAliasName();

  List<String> getEntityClassList();

  String getName();

  PsiElement setName(String newName);

  PsiElement getNameIdentifier();

}
