package ar.com.dccsoft.srytd.api;

import java.io.IOException;
import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import ar.com.dccsoft.srytd.utils.MDCUtils;
import ar.com.dccsoft.srytd.utils.MDCUtils.MDCKey;

@Provider
public class MDCFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Principal user = requestContext.getSecurityContext().getUserPrincipal();
		if(user != null) {
			MDCUtils.put(MDCKey.USER, user.getName());
		}
	}

}
