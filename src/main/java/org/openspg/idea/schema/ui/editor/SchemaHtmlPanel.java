package org.openspg.idea.schema.ui.editor;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.jcef.JCEFHtmlPanel;
import com.intellij.util.Url;
import com.intellij.util.Urls;
import org.cef.CefSettings;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.*;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;
import org.jetbrains.ide.BuiltInServerManager;
import org.openspg.idea.schema.ui.editor.jcef.SchemaResourceRequestHandler;
import org.openspg.idea.schema.ui.editor.server.PreviewStaticServer;
import org.openspg.idea.schema.ui.editor.server.ResourcesController;
import org.openspg.idea.schema.util.EditorUtils;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;

public class SchemaHtmlPanel extends JCEFHtmlPanel {

    private static final Logger logger = Logger.getInstance(SchemaHtmlPanel.class);

    private final Url HOME_URL = BuiltInServerManager.getInstance().addAuthToken(
            Objects.requireNonNull(
                    Urls.parseEncoded("http://localhost:" + BuiltInServerManager.getInstance().getPort()
                            + PreviewStaticServer.SERVLET_CONTEXT_PATH
                            + ResourcesController.SERVLET_PATH
                            + "/")));

    //private final Url HOME_URL = BuiltInServerManager.getInstance().addAuthToken(
    //        Objects.requireNonNull(
    //                Urls.parseEncoded("http://192.168.3.148:9000/")));

    private final String API_SCHEMA_FETCH = "schema/fetch";

    private final Project myProject;
    private final VirtualFile myFile;
    private byte[] resourceBytes = new byte[0];

    private CefRequestHandler requestHandler;
    private CefLifeSpanHandler lifeSpanHandler;
    private CefDisplayHandler displayHandler;

    public SchemaHtmlPanel(Project project, VirtualFile file) {
        super(null);
        this.myProject = project;
        this.myFile = file;
        initialize();
    }

    private void initialize() {
        loadResourceFile();

        getJBCefClient().addRequestHandler(requestHandler = new CefRequestHandlerAdapter() {
            @Override
            public CefResourceRequestHandler getResourceRequestHandler(CefBrowser browser, CefFrame frame, CefRequest request, boolean isNavigation, boolean isDownload, String requestInitiator, BoolRef disableDefaultHandling) {
                return new SchemaResourceRequestHandler(resourceBytes, req -> {
                    String requestUrl = req.getURL();
                    return resourceBytes.length > 0 && requestUrl.endsWith(API_SCHEMA_FETCH);
                });
            }
        }, getCefBrowser());

        getJBCefClient().addLifeSpanHandler(lifeSpanHandler = new CefLifeSpanHandlerAdapter() {
            @Override
            public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String target_url, String target_frame_name) {
                return true;
            }
        }, getCefBrowser());

        getJBCefClient().addDisplayHandler(displayHandler = new CefDisplayHandlerAdapter() {
            @Override
            public boolean onConsoleMessage(CefBrowser browser, CefSettings.LogSeverity level, String message, String source, int line) {
                logger.warn("Console: " + message);
                return true;
            }
        }, getCefBrowser());

        this.loadURL(HOME_URL.toString());
    }

    private void loadResourceFile() {
        PsiFile psiFile = PsiManager.getInstance(myProject).findFile(myFile);
        if (psiFile == null) {
            logger.error("Unparsed file: " + myFile.getName());
            this.resourceBytes = new byte[0];

        } else {
            String schemaString = EditorUtils.fileToJsonString(psiFile);
            logger.warn(schemaString.substring(0, Math.min(160, schemaString.length())));
            String dataString = "{\"code\":0,\"message\":\"success\",\"data\":" + schemaString + "}";
            this.resourceBytes = dataString.getBytes(StandardCharsets.UTF_8);
        }
    }

    @Override
    public void dispose() {
        getJBCefClient().removeRequestHandler(requestHandler, getCefBrowser());
        getJBCefClient().removeLifeSpanHandler(lifeSpanHandler, getCefBrowser());
        getJBCefClient().removeDisplayHandler(displayHandler, getCefBrowser());
        super.dispose();
    }

    //private String getStyle(boolean isTag) {
    //    try {
    //        EditorColorsSchemeImpl editorColorsScheme = (EditorColorsSchemeImpl) EditorColorsManager.getInstance().getGlobalScheme();
    //        Color defaultBackground = editorColorsScheme.getDefaultBackground();
    //
    //        Color scrollbarThumbColor = ScrollBarPainter.THUMB_OPAQUE_BACKGROUND.getDefaultColor();
    //        if (editorColorsScheme.getColor(ScrollBarPainter.THUMB_OPAQUE_BACKGROUND) != null) {
    //            scrollbarThumbColor = editorColorsScheme.getColor(ScrollBarPainter.THUMB_OPAQUE_BACKGROUND);
    //        }
    //        TextAttributes textAttributes = editorColorsScheme.getDirectlyDefinedAttributes().get("TEXT");
    //        Color text = null;
    //        if (textAttributes != null) {
    //            text = textAttributes.getForegroundColor();
    //        }
    //        String fontFamily = "font-family:\"" + editorColorsScheme.getEditorFontName() + "\",\"Helvetica Neue\",\"Luxi Sans\",\"DejaVu Sans\"," +
    //                "\"Hiragino Sans GB\",\"Microsoft Yahei\",sans-serif,\"Apple Color Emoji\",\"Segoe UI Emoji\",\"Noto Color Emoji\",\"Segoe UI Symbol\"," +
    //                "\"Android Emoji\",\"EmojiSymbols\";";
    //        StringBuilder sb = new StringBuilder(isTag ? "<style id=\"ideaStyle\">": "");
    //        sb.append(UIUtils.isDarkTheme() ? ".vditor--dark": ".vditor").append("{--panel-background-color:").append(toHexColor(defaultBackground))
    //                .append(";--textarea-background-color:").append(toHexColor(defaultBackground)).append(";");
    //        sb.append("--toolbar-background-color:").append(toHexColor(JBColor.background())).append(";");
    //        sb.append("}");
    //        sb.append("::-webkit-scrollbar-track {background-color:").append(toHexColor(defaultBackground)).append(";}");
    //        sb.append("::-webkit-scrollbar-thumb {background-color:").append(toHexColor(scrollbarThumbColor)).append(";}");
    //        sb.append(".vditor-reset {font-size:").append(editorColorsScheme.getEditorFontSize()).append("px;");
    //        sb.append(fontFamily);
    //        if (text != null) {
    //            sb.append("color:").append(toHexColor(text)).append(";");
    //        }
    //        sb.append("}");
    //        sb.append(" body{background-color: ").append(toHexColor(defaultBackground)).append(";}");
    //        sb.append(isTag ? "</style>": "");
    //        return sb.toString();
    //    } catch (Exception e) {
    //        return "";
    //    }
    //
    //}

    private String toHexColor(Color color) {
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        return String.format("rgba(%s,%s,%s,%s)", color.getRed(), color.getGreen(), color.getBlue(), df.format(color.getAlpha() / (float) 255));
    }

    public void updateSchema() {
        loadResourceFile();
        String script = "document.getElementById('schema-diagram-refresh-button').click();";
        getCefBrowser().executeJavaScript(script, getCefBrowser().getURL(), 0);
    }
}
