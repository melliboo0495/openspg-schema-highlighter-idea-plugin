package org.openspg.idea.schema.ui.editor.jcef;

import org.jetbrains.annotations.NotNull;

public abstract class SchemaResourceSupplier {

    public abstract boolean isSupported(@NotNull String requestUrl);

    public abstract Resource getResource(@NotNull String requestUrl);

    public interface Resource {
        @NotNull String getContentType();

        byte @NotNull [] getContent();
    }
}
