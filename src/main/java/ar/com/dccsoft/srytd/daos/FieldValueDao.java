package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.SQLSERVER;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.FieldValue;

public class FieldValueDao {

	@SuppressWarnings("unchecked")
	public List<FieldValue> readFieldValues(Date from, Date to) {
		Criteria c = SQLSERVER.currentSession().createCriteria(FieldValue.class);
		c.add(Restrictions.between("timestamp", from, to));
		return c.list();
	}
}
