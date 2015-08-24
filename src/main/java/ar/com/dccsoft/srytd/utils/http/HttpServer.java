package ar.com.dccsoft.srytd.utils.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
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

		WebAppContext context = new WebAppContext();
		context.setContextPath(Config.getContextPath());
		context.setServer(server);
		context.setWar("webapp");
		server.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");
		jerseyServlet.setInitOrder(0);

		// Tells the Jersey Servlet which REST service/class to load.
		jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "ar.com.dccsoft.srytd.api");

		return server;
	}
}