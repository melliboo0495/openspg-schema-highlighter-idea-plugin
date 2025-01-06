package org.openspg.idea.schema.grammar;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.lang.parser.SchemaParser;


public class TraceableSchemaParser extends SchemaParser {

    @Override
    public @NotNull ASTNode parse(IElementType root, PsiBuilder builder) {
        parseLight(root, builder);
        ASTNode node = builder.getTreeBuilt();
        traceTree(node);
        return node;
    }

    public static void traceTree(ASTNode root) {
        System.out.println("=========== Nodes");
        traceTree(root, 0);
        System.out.println("===========");
    }

    public static void traceTree(ASTNode root, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print("  ");
        }
        System.out.println(root.getElementType() + " " + root.getClass().getName() + " <<" + root.getText().replace("\n", "\\n") + ">>");

        for (ASTNode child = root.getFirstChildNode(); child != null; child = child.getTreeNext()) {
            traceTree(child, indent + 1);
        }
    }

}
