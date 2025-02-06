package org.openspg.idea.schema.ui.editor.jcef;

import com.intellij.util.io.HttpRequests;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.callback.CefCallback;
import org.cef.handler.CefResourceHandler;
import org.cef.handler.CefResourceHandlerAdapter;
import org.cef.handler.CefResourceRequestHandlerAdapter;
import org.cef.misc.IntRef;
import org.cef.misc.StringRef;
import org.cef.network.CefRequest;
import org.cef.network.CefResponse;

import java.io.IOException;
import java.util.function.Predicate;

public class SchemaResourceRequestHandler extends CefResourceRequestHandlerAdapter {

    private final byte[] resource;
    private final Predicate<HttpRequests.Request> filter;

    public SchemaResourceRequestHandler(byte[] resource, Predicate<HttpRequests.Request> filter) {
        this.resource = resource;
        this.filter = filter;
    }

    @Override
    public CefResourceHandler getResourceHandler(CefBrowser browser, CefFrame frame, CefRequest request) {
        try {
            return HttpRequests.request(request.getURL())
                    .throwStatusCodeException(false)
                    .connect((HttpRequests.RequestProcessor<CefResourceHandler>) req -> {
                        if (!filter.test(req)) {
                            return null;
                        }

                        return new CefResourceHandlerAdapter() {
                            private int position = 0;

                            @Override
                            public boolean processRequest(CefRequest req, CefCallback callback) {
                                callback.Continue();
                                return true;
                            }

                            @Override
                            public void getResponseHeaders(CefResponse response, IntRef responseLength, StringRef redirectUrl) {
                                response.setMimeType("application/json; charset=utf-8");
                                responseLength.set(resource.length);
                            }

                            @Override
                            public boolean readResponse(byte[] dataOut, int bytesToRead, IntRef bytesRead, CefCallback callback) {
                                if (resource.length <= position) {
                                    bytesRead.set(0);
                                    return false;
                                }

                                int chunkSize = Math.min(bytesToRead, resource.length - position);
                                System.arraycopy(resource, position, dataOut, 0, chunkSize);
                                position += chunkSize;
                                bytesRead.set(chunkSize);
                                return true;
                            }
                        };
                    });

        } catch (IOException io) {
            return null;
        }
    }

}
