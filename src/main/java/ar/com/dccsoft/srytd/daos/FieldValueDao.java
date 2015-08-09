package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.Datasource.SQLSERVER;
import static ar.com.dccsoft.srytd.utils.TransactionManager.transactional;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.FieldValue;

public class FieldValueDao {

	@SuppressWarnings("unchecked")
	public List<FieldValue> readFieldValues(Date from, Date to) {
		return transactional(SQLSERVER, (session) -> {
			Criteria c = session.createCriteria(FieldValue.class);
			c.add(Restrictions.between("timestamp", from, to));
			return c.list();
		});
	}
}
