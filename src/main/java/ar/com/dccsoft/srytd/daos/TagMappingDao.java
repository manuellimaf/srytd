package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.List;

import ar.com.dccsoft.srytd.model.Device;

public class TagMappingDao {

	@SuppressWarnings("unchecked")
	public List<Device> getAll() {
		return transactional(MySQL, (session) -> {
			return session.createCriteria(Device.class).list();
		});
	}

}
