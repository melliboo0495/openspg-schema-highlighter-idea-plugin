// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import org.openspg.idea.schema.psi.impl.SchemaNamedElementImpl;
import org.openspg.idea.schema.psi.impl.SchemaPsiImplUtil;
import org.openspg.idea.lang.psi.*;

public class SchemaEntityInfoImpl extends SchemaNamedElementImpl implements SchemaEntityInfo {

  public SchemaEntityInfoImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitEntityInfo(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getEntityName() {
    return SchemaPsiImplUtil.getEntityName(this);
  }

  @Override
  public String getEntityAliasName() {
    return SchemaPsiImplUtil.getEntityAliasName(this);
  }

  @Override
  public String getEntityType() {
    return SchemaPsiImplUtil.getEntityType(this);
  }

  @Override
  public String getName() {
    return SchemaPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return SchemaPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return SchemaPsiImplUtil.getNameIdentifier(this);
  }

}
