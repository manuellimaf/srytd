package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.User;

public class UserDao {

	@SuppressWarnings("unchecked")
	public List<Process> getPage(Integer start, Integer limit) {
		String qStr = "select distinct u from " + User.class.getName() + " u order by u.username";
		Query query = MySQL.currentSession().createQuery(qStr);
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	
	public Long countAll() {
		return (Long) MySQL.currentSession().createCriteria(User.class)
				.setProjection(Projections.countDistinct("username")).uniqueResult();
	}

	public User find(Long id) {
		return (User) MySQL.currentSession().get(User.class, id);
	}

	public void update(User user) {
		MySQL.currentSession().update(user);
	}

	public void save(User user) {
		MySQL.currentSession().save(user);
	}

	public User findByName(String username) {
		return (User) MySQL.currentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.setMaxResults(1)
				.uniqueResult();
	}

}
