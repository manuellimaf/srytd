package ar.com.dccsoft.srytd.daos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.FieldValue;
import ar.com.dccsoft.srytd.utils.HibernateUtil;
import static ar.com.dccsoft.srytd.utils.TransactionManager.transactional;

public class FieldValueDao {

	@SuppressWarnings("unchecked")
	public List<FieldValue> readFieldValues(Date from, Date to) {
		return transactional((session) -> {
			Criteria c = HibernateUtil.currentSession().createCriteria(FieldValue.class);
			c.add(Restrictions.between("timestamp", from, to));
			return c.list();
		});
	}
}
