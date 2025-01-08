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
import org.openspg.idea.schema.psi.impl.SchemaPsiImplUtil;

public class SchemaNamespaceImpl extends ASTWrapperPsiElement implements SchemaNamespace {

  public SchemaNamespaceImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitNamespace(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getValue() {
    return SchemaPsiImplUtil.getValue(this);
  }

}
