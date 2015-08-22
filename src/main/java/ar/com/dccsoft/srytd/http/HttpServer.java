package ar.com.dccsoft.srytd.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.utils.Config;

public class HttpServer {

	private static Logger logger = LoggerFactory.getLogger(HttpServer.class);
	private static Server server = createJettyServer();

	public static void start() {
		logger.info("Starting Http server");
		try {
			server.start();
			logger.info(String.format("Http server started. Listening at *:%d", Config.getHttpPort()));
		} catch (Exception e) {
			throw new RuntimeException("Error starting Jetty Http Server", e);
		}
	}

	private static Server createJettyServer() {
		Server server = new Server(Config.getHttpPort());
		//
		// val config = classOf[LiftConfig]
		// val filterHolder = new FilterHolder(classOf[LiftFilter])
		// filterHolder.setName("LiftFilter")
		// filterHolder.setInitParameter("bootloader", config.getName)
		//
		WebAppContext context = new WebAppContext();
		context.setContextPath(Config.getContextPath());
		context.setServer(server);
		context.setWar("webapp");
		// context.addFilter(filterHolder, "/*", null)
		//
		server.setHandler(context);
		return server;
	}
}
