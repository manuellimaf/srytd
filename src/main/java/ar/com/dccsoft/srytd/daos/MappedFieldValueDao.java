package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ar.com.dccsoft.srytd.model.MappedFieldValue;

public class MappedFieldValueDao {

	public MappedFieldValue save(MappedFieldValue value) {
		MySQL.currentSession().save(value);
		return value;
	}

	@SuppressWarnings("unchecked")
	public List<MappedFieldValue> filterByProcessId(Long processId) {
		Criteria c = MySQL.currentSession().createCriteria(MappedFieldValue.class)
			.createCriteria("process").add(Restrictions.idEq(processId))
			.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<MappedFieldValue> getPageForProcessId(Long processId, Integer start, Integer limit) {
		String qStr = "select distinct v from " + MappedFieldValue.class.getName() + " v where v.process.id = :pId order by v.deviceId";
		Query query = MySQL.currentSession().createQuery(qStr);
		query.setParameter("pId", processId);
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	
	public Long countAllForProcessId(Long processId) {
		return (Long) MySQL.currentSession()
				.createCriteria(MappedFieldValue.class, "mfv")
				.createCriteria("process")
				.add(Restrictions.idEq(processId))
				.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
				.setProjection(Projections.countDistinct("mfv.id"))
				.uniqueResult();
	}

}
