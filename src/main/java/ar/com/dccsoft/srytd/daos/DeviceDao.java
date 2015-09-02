package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import ar.com.dccsoft.srytd.model.Device;
import ar.com.dccsoft.srytd.model.Process;

public class DeviceDao {

	@SuppressWarnings("unchecked")
	public List<Device> getAll() {
		return MySQL.currentSession().createCriteria(Device.class).list();
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

	public void delete(Long id) {
		Device device = (Device) MySQL.currentSession().load(Device.class, id);
		MySQL.currentSession().delete(device);
	}

}
