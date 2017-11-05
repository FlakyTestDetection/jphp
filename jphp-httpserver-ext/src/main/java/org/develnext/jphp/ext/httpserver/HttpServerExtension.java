package org.develnext.jphp.ext.httpserver;

import org.develnext.jphp.ext.httpserver.classes.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.log.JavaUtilLog;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import php.runtime.env.CompileScope;
import php.runtime.ext.support.Extension;

import java.util.logging.Level;

class NoLogging implements Logger {
    @Override public String getName() { return "no"; }
    @Override public void warn(String msg, Object... args) { }
    @Override public void warn(Throwable thrown) {
        thrown.printStackTrace();
    }

    @Override public void warn(String msg, Throwable thrown) {
        System.err.println(msg);
        thrown.printStackTrace();
    }

    @Override public void info(String msg, Object... args) { }
    @Override public void info(Throwable thrown) { }
    @Override public void info(String msg, Throwable thrown) { }
    @Override public boolean isDebugEnabled() { return false; }
    @Override public void setDebugEnabled(boolean enabled) { }
    @Override public void debug(String msg, Object... args) { }
    @Override public void debug(String msg, long value) {}
    @Override public void debug(Throwable thrown) { }
    @Override public void debug(String msg, Throwable thrown) { }
    @Override public Logger getLogger(String name) { return this; }
    @Override public void ignore(Throwable ignored) { }
}

public class HttpServerExtension extends Extension {
    public static final String NS = "php\\http";

    @Override
    public Status getStatus() {
        return Status.EXPERIMENTAL;
    }

    @Override
    public String[] getPackageNames() {
        return new String[] { "http" };
    }

    @Override
    public void onRegister(CompileScope scope) {
        registerClass(scope, PHttpServer.class);
        registerClass(scope, PHttpServerRequest.class);
        registerClass(scope, PHttpServerResponse.class);
        registerClass(scope, PHttpRouteHandler.class);
        registerClass(scope, PHttpRedirectHandler.class);
        registerClass(scope, PHttpDownloadFileHandler.class);
        registerClass(scope, PHttpResourceHandler.class);

        Log.setLog(new NoLogging());
    }
}
