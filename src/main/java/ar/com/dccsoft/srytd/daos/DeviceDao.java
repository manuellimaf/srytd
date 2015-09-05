package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.model.Process;

public class DeviceDao {

	@SuppressWarnings("unchecked")
	public List<Device> getAll() {
		return MySQL.currentSession().createCriteria(Device.class).addOrder(Order.asc("name")).list();
	}

	@SuppressWarnings("unchecked")
	public List<Process> getPage(Integer start, Integer limit) {
		String qStr = "select distinct d from " + Device.class.getName() + " d order by d.name";
		Query query = MySQL.currentSession().createQuery(qStr);
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	
	public Long countAll() {
		return (Long) MySQL.currentSession().createCriteria(Device.class)
				.setProjection(Projections.countDistinct("id")).uniqueResult();
	}

	public Device findDevice(Long id) {
		return (Device) MySQL.currentSession().get(Device.class, id);
	}
	
	public void delete(Long id) {
		Device device = findDevice(id);
		MySQL.currentSession().delete(device);
	}

	public void save(Device device) {
		MySQL.currentSession().save(device);
	}

	public void update(Device device) {
		MySQL.currentSession().update(device);
	}

	@SuppressWarnings("unchecked")
	public List<Device> findByName(String name) {
		return MySQL.currentSession()
				.createCriteria(Device.class)
				.add(Restrictions.eq("name", name))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Device> findByTag(String tag) {
		return MySQL.currentSession()
				.createCriteria(Device.class)
				.add(Restrictions.eq("tag", tag))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.list();
	}

}
