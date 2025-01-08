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

public class SchemaEntityImpl extends ASTWrapperPsiElement implements SchemaEntity {

  public SchemaEntityImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitEntity(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SchemaEntityInfo getEntityInfo() {
    return findNotNullChildByClass(SchemaEntityInfo.class);
  }

  @Override
  @NotNull
  public List<SchemaEntityMeta> getEntityMetaList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaEntityMeta.class);
  }

  @Override
  public String getName() {
    return SchemaPsiImplUtil.getName(this);
  }

}
