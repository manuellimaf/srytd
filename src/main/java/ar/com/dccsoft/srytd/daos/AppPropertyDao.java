package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.AppProperty;

public class AppPropertyDao {

	public AppProperty getProperty(String key) {
		return transactional(MySQL, (s) -> {
			return (AppProperty) s.createCriteria(AppProperty.class).add(Restrictions.eq("key", key)).uniqueResult();
		});
	}

	public void update(String key, String value) {
		Query q = MySQL.currentSession().createQuery(
				"update " + AppProperty.class.getName() + " prop set prop.value = :value where key = :key");
		q.setParameter("key", key);
		q.setParameter("value", value);
		q.executeUpdate();
	}
}
