package org.openspg.idea.schema.structureView.viewElement;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.lang.psi.SchemaEntityMeta;

public class SchemaEntityMetaStructureViewElement extends SchemaStructureViewElement<SchemaEntityMeta> {

    public SchemaEntityMetaStructureViewElement(SchemaEntityMeta element) {
        super(element);
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = myElement.getName();
        return name != null ? name : "";
    }

    @Override
    protected PresentationData createPresentation(SchemaEntityMeta element) {
        return new PresentationData(
                element.getName(),
                null,
                null,
                null
        );
    }

    @Override
    public TreeElement @NotNull [] getChildren() {
        //List<PsiElement> elements = PsiTreeUtil.getChildrenOfTypeAsList(myElement, SchemaEntity.class);
        //
        //List<TreeElement> treeElements = new ArrayList<>(elements.size());
        //
        //for (PsiElement element : elements) {
        //    treeElements.add(new SchemaEntityStructureViewElement((NavigatablePsiElement) element));
        //}
        //
        //return treeElements.toArray(new TreeElement[0]);
        return EMPTY_ARRAY;
    }

}
