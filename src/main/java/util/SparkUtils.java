package util;

import org.eclipse.jetty.server.AbstractNCSARequestLog;
import spark.embeddedserver.EmbeddedServers;
import spark.embeddedserver.jetty.EmbeddedJettyFactory;

import org.apache.log4j.Logger;

public class SparkUtils {
    public static void createServerWithRequestLog(Logger logger) {
        EmbeddedJettyFactory factory = createEmbeddedJettyFactoryWithRequestLog(logger);
        EmbeddedServers.add(EmbeddedServers.Identifiers.JETTY, factory);
    }

    private static EmbeddedJettyFactory createEmbeddedJettyFactoryWithRequestLog(Logger logger) {
        AbstractNCSARequestLog requestLog = new RequestLogFactory(logger).create();
        return new EmbeddedJettyFactoryConstructor(requestLog).create();
    }
}