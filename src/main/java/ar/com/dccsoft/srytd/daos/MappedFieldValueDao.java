package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.Date;
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
	public List<MappedFieldValue> readManualValues(Date from, Date to) {
		Criteria c = MySQL.currentSession().createCriteria(MappedFieldValue.class);
		c.add(Restrictions.between("timestamp", from, to));
		return c.list();
	}
}
