// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SchemaEntity extends PsiElement {

  @NotNull
  SchemaEntityInfo getEntityInfo();

  @NotNull
  List<SchemaEntityMeta> getEntityMetaList();

  String getName();

}
