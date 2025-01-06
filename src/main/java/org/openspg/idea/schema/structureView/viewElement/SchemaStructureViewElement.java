package org.openspg.idea.schema.structureView.viewElement;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.NavigatablePsiElement;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public abstract class SchemaStructureViewElement<T extends PsiElement> implements StructureViewTreeElement, SortableTreeElement {

    protected final T myElement;

    public SchemaStructureViewElement(T element) {
        this.myElement = element;
    }

    @Override
    public Object getValue() {
        return myElement;
    }

    @Override
    public void navigate(boolean requestFocus) {
        if (myElement instanceof NavigatablePsiElement) {
            ((NavigatablePsiElement) myElement).navigate(requestFocus);
        }
    }

    @Override
    public boolean canNavigate() {
        if (myElement instanceof NavigatablePsiElement) {
            return ((NavigatablePsiElement) myElement).canNavigate();
        }
        return false;
    }

    @Override
    public boolean canNavigateToSource() {
        if (myElement instanceof NavigatablePsiElement) {
            return ((NavigatablePsiElement) myElement).canNavigateToSource();
        }
        return false;
    }

    @NotNull
    @Override
    public ItemPresentation getPresentation() {
        if (myElement instanceof NavigatablePsiElement) {
            ItemPresentation presentation = ((NavigatablePsiElement) myElement).getPresentation();
            if (presentation == null) {
                presentation = createPresentation(myElement);
            }
            return presentation;
        }
        return createPresentation(myElement);
    }

    protected PresentationData createPresentation(T element) {
        return new PresentationData();
    }

}
