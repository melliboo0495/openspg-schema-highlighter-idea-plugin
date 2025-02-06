package org.openspg.idea.schema.ui.editor;

import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.CaretListener;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.impl.text.TextEditorProvider;
import com.intellij.openapi.fileTypes.FileTypeRegistry;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.openspg.idea.schema.SchemaFileType;

public class SchemaSplitEditorProvider implements FileEditorProvider, DumbAware {

    public SchemaSplitEditorProvider() {

    }

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return isSchemaFileType(project, file);
    }


    @Override
    public boolean acceptRequiresReadAction() {
        return false;
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        TextEditor editor = (TextEditor) TextEditorProvider.getInstance()
                .createEditor(project, file);
        SchemaPreviewEditor previewEditor = (SchemaPreviewEditor) SchemaPreviewEditorProvider.getInstance()
                .createEditor(project, file);

        editor.getEditor().getCaretModel().addCaretListener(new CaretListener() {
            public void caretPositionChanged(@NotNull CaretEvent event) {
                //System.out.println("caret position changed " + event);
                //int offset = Objects.requireNonNull(event.getCaret()).getOffset();
                //
                //PsiFile psiFile = PsiManager.getInstance(project).findFile(file);
                //if (psiFile == null) {
                //    System.out.println("psiFile is null");
                //    return;
                //}
                //
                //PsiElement element = psiFile.findElementAt(offset);
                //if (element == null) {
                //    System.out.println("element is null");
                //    return;
                //}
                //SchemaEntity entity = PsiTreeUtil.getParentOfType(element, SchemaEntity.class);
                //if (entity == null) {
                //    System.out.println("entity is null");
                //    return;
                //}
                //System.out.println("Current Entity: " + entity.getEntityInfo().getEntityName());
            }
        });

        return new SchemaSplitEditor(editor, previewEditor);
    }

    @Override
    public @NotNull String getEditorTypeId() {
        return "openspg-schema-split-editor";
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }

    public static boolean isSchemaFileType(@NotNull Project project, @NotNull VirtualFile file) {
        if (file.isDirectory() || !file.exists()) {
            return false;
        }

        if (project.isDisposed()) {
            return false;
        }
        return FileTypeRegistry.getInstance().isFileOfType(file, SchemaFileType.INSTANCE);
    }

}
