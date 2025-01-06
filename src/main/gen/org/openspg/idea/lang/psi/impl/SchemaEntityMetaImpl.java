// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.openspg.idea.schema.psi.impl.SchemaPsiImplUtil;
import org.openspg.idea.lang.psi.*;

public class SchemaEntityMetaImpl extends ASTWrapperPsiElement implements SchemaEntityMeta {

  public SchemaEntityMetaImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitEntityMeta(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SchemaAttribute> getAttributeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaAttribute.class);
  }

  @Override
  public String getName() {
    return SchemaPsiImplUtil.getName(this);
  }

}
