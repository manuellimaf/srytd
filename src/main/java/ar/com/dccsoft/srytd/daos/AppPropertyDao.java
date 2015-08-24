package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.model.AppProperty;

public class AppPropertyDao {

	private static Logger logger = LoggerFactory.getLogger(AppPropertyDao.class);

	public AppProperty getProperty(String key) {
		return transactional(MySQL, (s) -> {
			return (AppProperty) s.createCriteria(AppProperty.class).add(Restrictions.eq("key", key)).uniqueResult();
		});
	}

	public void upsert(String key, String value) {
		logger.info(String.format("Trying to update (%s -> %s)", key, value));
		Query q = MySQL.currentSession().createQuery(
				"update " + AppProperty.class.getName() + " prop set prop.value = :value where key = :key");
		q.setParameter("key", key);
		q.setParameter("value", value);

		int updates = q.executeUpdate();
		if (updates == 0) {
			logger.info("Key '%s' not found, register new value", key);
			// Not found, register new value
			AppProperty prop = new AppProperty();
			prop.setKey(key);
			prop.setValue(value);
			MySQL.currentSession().save(prop);
		}
	}
}
