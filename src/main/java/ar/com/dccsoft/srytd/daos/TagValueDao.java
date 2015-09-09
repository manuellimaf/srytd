package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.SQLSERVER;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import ar.com.dccsoft.srytd.model.TagValue;

public class TagValueDao {

	
	@SuppressWarnings("unchecked")
	public List<TagValue> readTagValue(Date from, Date to, String tag) {
		Query q = SQLSERVER.currentSession().getNamedQuery("tag.value");
		q.setParameter("from", from);
		q.setParameter("to", to);
		q.setParameter("tagname", tag);
		return q.list();
	}
}
