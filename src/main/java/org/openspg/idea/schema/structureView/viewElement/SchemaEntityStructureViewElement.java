package org.openspg.idea.schema.structureView.viewElement;

import com.intellij.icons.AllIcons;
import com.intellij.ide.projectView.PresentationData;
import com.intellij.ide.util.treeView.smartTree.TreeElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.lang.psi.SchemaEntity;
import org.openspg.idea.lang.psi.SchemaEntityInfo;
import org.openspg.idea.lang.psi.SchemaEntityMeta;

import java.util.ArrayList;
import java.util.List;

public class SchemaEntityStructureViewElement extends SchemaStructureViewElement<SchemaEntity> {

    public SchemaEntityStructureViewElement(SchemaEntity element) {
        super(element);
    }

    @NotNull
    @Override
    public String getAlphaSortKey() {
        String name = myElement.getName();
        return name != null ? name : "";
    }

    @Override
    protected PresentationData createPresentation(SchemaEntity element) {
        SchemaEntityInfo info = element.getEntityInfo();
        return new PresentationData(
                info.getEntityName(),
                info.getEntityType() + ": " + info.getEntityAliasName(),
                AllIcons.Nodes.Class,
                null
        );
    }

    @Override
    public TreeElement @NotNull [] getChildren() {
        List<SchemaEntityMeta> elements = PsiTreeUtil.getChildrenOfTypeAsList(myElement, SchemaEntityMeta.class);

        List<TreeElement> treeElements = new ArrayList<>(elements.size());

        for (SchemaEntityMeta element : elements) {
            treeElements.add(new SchemaEntityMetaStructureViewElement(element));
        }

        return treeElements.toArray(new TreeElement[0]);
    }

}
