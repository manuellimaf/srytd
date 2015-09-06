package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.Date;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.api.dto.UserDTO;
import ar.com.dccsoft.srytd.daos.UserDao;
import ar.com.dccsoft.srytd.model.Role;
import ar.com.dccsoft.srytd.model.User;

public class UserService {

	private UserDao dao = new UserDao();
	
	public Page getPage(Integer start, Integer limit) {
		return transactional(MySQL, (session) -> {
			return new Page(dao.getPage(start, limit), dao.countAll());
		});
	}

	public void disable(Long id) {
		Boolean enabled = false;
		changeState(id, enabled);
	}

	public void enable(Long id) {
		Boolean enabled = true;
		changeState(id, enabled);
	}

	public void changeState(Long id, Boolean enabled) {
		transactional(MySQL, (session) -> {
			User user = dao.find(id);
			user.setEnabled(enabled);
			dao.update(user);
			return null;
		});
	}

	public void createUser(UserDTO dto, String username) {
		transactional(MySQL, (session) -> {
			User user = new User();
			user.setCreatedBy(username);
			user.setDateCreated(new Date());
			user.setEnabled(true);
			user = copy(dto, user);
			dao.save(user);
			return null;
		});
	}

	public void updateUser(UserDTO dto) {
		transactional(MySQL, (session) -> {
			User user = dao.find(dto.getId());
			user = copy(dto, user);
			dao.update(user);
			return null;
		});
	}

	public User getUser(Long id) {
		return transactional(MySQL, (s) -> dao.find(id));
	}
	
	public Boolean userExists(Long id) {
		return getUser(id) != null;
	}
	
	private User copy(UserDTO dto, User user) {
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRole(Role.valueOf(dto.getRole()));
		return user;
	}

	public boolean userExists(String username) {
		return transactional(MySQL, (s) -> dao.findByName(username) != null);
	}

}
