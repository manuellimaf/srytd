package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.DeviceMapping;
import ar.com.dccsoft.srytd.model.Process;

public class DeviceMappingDao {

	@SuppressWarnings("unchecked")
	public List<DeviceMapping> getAll() {
		return MySQL.currentSession().createCriteria(DeviceMapping.class).addOrder(Order.asc("name")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Process> getPage(Integer start, Integer limit) {
		String qStr = "select distinct d from " + DeviceMapping.class.getName() + " d order by d.name";
		Query query = MySQL.currentSession().createQuery(qStr);
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	
	public Long countAll() {
		return (Long) MySQL.currentSession().createCriteria(DeviceMapping.class)
				.setProjection(Projections.countDistinct("id")).uniqueResult();
	}

	public DeviceMapping findDevice(Long id) {
		return (DeviceMapping) MySQL.currentSession().get(DeviceMapping.class, id);
	}
	
	public void delete(Long id) {
		DeviceMapping device = findDevice(id);
		MySQL.currentSession().delete(device);
	}

	public void save(DeviceMapping device) {
		MySQL.currentSession().save(device);
	}

	public void update(DeviceMapping device) {
		MySQL.currentSession().update(device);
	}

	@SuppressWarnings("unchecked")
	public List<DeviceMapping> findByName(String name) {
		return MySQL.currentSession()
				.createCriteria(DeviceMapping.class)
				.add(Restrictions.eq("name", name))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<DeviceMapping> findByTag(String tag) {
		return MySQL.currentSession()
				.createCriteria(DeviceMapping.class)
				.add(Restrictions.eq("tag", tag))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
	}

}
