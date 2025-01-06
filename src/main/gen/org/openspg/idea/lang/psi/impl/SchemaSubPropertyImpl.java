// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.openspg.idea.grammar.psi.SchemaTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.openspg.idea.lang.psi.*;

public class SchemaSubPropertyImpl extends ASTWrapperPsiElement implements SchemaSubProperty {

  public SchemaSubPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitSubProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SchemaSubPropertyInfo getSubPropertyInfo() {
    return findNotNullChildByClass(SchemaSubPropertyInfo.class);
  }

  @Override
  @NotNull
  public List<SchemaSubPropertyMeta> getSubPropertyMetaList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaSubPropertyMeta.class);
  }

}
