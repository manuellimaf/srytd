package ar.com.dccsoft.srytd.utils.security;

import java.security.Principal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.securityfilter.realm.SimpleSecurityRealmBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.User;
import ar.com.dccsoft.srytd.services.UserService;

import com.google.common.collect.Maps;

public class SecurityRealm extends SimpleSecurityRealmBase {

	private static Map<Principal, User> users = Maps.newConcurrentMap();
	private UserService service = new UserService();

	private Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

	@Override
	public boolean booleanAuthenticate(String username, String password) {
		
		if (StringUtils.isBlank(username)) {
			logger.info("Usuario no ingresado");
			return false;
		}

		User user = service.findByName(username);

		if (user == null || !user.getPassword().equals(password) || !user.getEnabled()) {
			logger.info("Usuario inválido");
			return false;
		}

		logger.info("Usuario logueado: " + user.getUsername());
		return true;
	}

	@Override
	public boolean isUserInRole(Principal authenticated, String role) {
		if (authenticated == null) {
			logger.info("Usuario inválido");
			return false;
		}

		User user = users.get(authenticated);
		if (user == null) {
			user = service.findByName(authenticated.getName());
			users.put(authenticated, user);
		} 
		if (user.getRole() == null || !user.hasRole(role)) {
			return false;       	
		}

		return true;
	}

}
