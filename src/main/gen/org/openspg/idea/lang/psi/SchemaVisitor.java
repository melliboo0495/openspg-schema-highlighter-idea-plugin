// This is a generated file. Not intended for manual editing.
package org.openspg.idea.lang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import org.openspg.idea.schema.psi.SchemaNamedElement;

public class SchemaVisitor extends PsiElementVisitor {

  public void visitAttribute(@NotNull SchemaAttribute o) {
    visitPsiElement(o);
  }

  public void visitAttributeInfo(@NotNull SchemaAttributeInfo o) {
    visitPsiElement(o);
  }

  public void visitAttributeMeta(@NotNull SchemaAttributeMeta o) {
    visitPsiElement(o);
  }

  public void visitEntity(@NotNull SchemaEntity o) {
    visitPsiElement(o);
  }

  public void visitEntityInfo(@NotNull SchemaEntityInfo o) {
    visitNamedElement(o);
  }

  public void visitEntityMeta(@NotNull SchemaEntityMeta o) {
    visitPsiElement(o);
  }

  public void visitNamespace(@NotNull SchemaNamespace o) {
    visitPsiElement(o);
  }

  public void visitSubProperty(@NotNull SchemaSubProperty o) {
    visitPsiElement(o);
  }

  public void visitSubPropertyInfo(@NotNull SchemaSubPropertyInfo o) {
    visitPsiElement(o);
  }

  public void visitSubPropertyMeta(@NotNull SchemaSubPropertyMeta o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull SchemaNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
