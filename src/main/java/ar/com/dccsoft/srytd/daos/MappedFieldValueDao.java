package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.MappedFieldValue;

public class MappedFieldValueDao {

	public MappedFieldValue save(MappedFieldValue value) {
		MySQL.currentSession().save(value);
		return value;
	}

	@SuppressWarnings("unchecked")
	public List<MappedFieldValue> filterByProcessId(Long processId) {
		Criteria c = MySQL.currentSession().createCriteria(MappedFieldValue.class);
		c.createCriteria("process").add(Restrictions.idEq(processId));
		return c.list();
	}
}
