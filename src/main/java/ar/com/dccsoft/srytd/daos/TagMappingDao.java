package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.TransactionManager.transactional;

import java.util.List;

import ar.com.dccsoft.srytd.model.TagMapping;

public class TagMappingDao {

	@SuppressWarnings("unchecked")
	public List<TagMapping> getAll() {
    	return transactional(MySQL, (session) -> {
    		return session.createCriteria(TagMapping.class).list();
    	});
	}

	
}

