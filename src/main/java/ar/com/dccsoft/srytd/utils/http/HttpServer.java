package ar.com.dccsoft.srytd.utils.http;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.securityfilter.authenticator.FormAuthenticator;
import org.securityfilter.filter.SecurityFilter;
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
			server.join();
		} catch (Exception e) {
			throw new RuntimeException("Error starting Jetty Http Server", e);
		}
	}

	private static Server createJettyServer() {
		Server server = new Server(Config.getHttpPort());

		String webappDir = HttpServer.class.getClassLoader().getResource("webapp").toExternalForm();
		WebAppContext context = new WebAppContext();
		context.setContextPath(Config.getContextPath());
		context.setServer(server);
		context.setResourceBase(webappDir);
		context.setWelcomeFiles(new String[] {"/login.html"});
		context.getSessionHandler().getSessionManager().setMaxInactiveInterval(Integer.MAX_VALUE);
		
		FilterHolder securityFilterHolder = new FilterHolder(SecurityFilter.class);
		securityFilterHolder.setInitParameter(SecurityFilter.CONFIG_FILE_KEY, "/WEB-INF/classes/security/securityfilter-config.xml");
		securityFilterHolder.setInitParameter("validate", "true");
		securityFilterHolder.setInitParameter(FormAuthenticator.LOGIN_SUBMIT_PATTERN_KEY, "/api/auth");
		context.addFilter(securityFilterHolder, "*.html", EnumSet.of(DispatcherType.REQUEST));
		context.addFilter(securityFilterHolder, "/api/*", EnumSet.of(DispatcherType.REQUEST));
		
		server.setHandler(context);

		ResourceConfig application = new ResourceConfig()
        	.packages("com.fasterxml.jackson.jaxrs.json;"
        			+ "jersey.jetty.embedded;"
        			// Tells the Jersey Servlet which REST service/class to load.
        			+ "ar.com.dccsoft.srytd.api")
        	.register(JacksonFeature.class);
		
		ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(application));
        jerseyServlet.setInitOrder(0);
		
		context.addServlet(jerseyServlet, "/api/*");

		return server;
	}
}
