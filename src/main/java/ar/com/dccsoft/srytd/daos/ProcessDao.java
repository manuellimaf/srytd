package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;

import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessStatus;

public class ProcessDao {

	public Process create(Date from, String username) {
		Process p = new Process();
		p.setStartDate(new Date());
		p.setStartedBy(username);
		p.setValuesFrom(from);
		p.setStatus(ProcessStatus.STARTED);
		MySQL.currentSession().save(p);
		return p;
	}

	public Process find(Long id) {
		return (Process) MySQL.currentSession().get(Process.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Process> getPage(Integer start, Integer limit) {
		String qStr = "select distinct p from " + Process.class.getName() + " p order by p.id desc";
		Query query = MySQL.currentSession().createQuery(qStr);
		return query.setFirstResult(start).setMaxResults(limit).list();
	}
	
	public Long countAll() {
		return (Long) MySQL.currentSession().createCriteria(Process.class)
				.setProjection(Projections.countDistinct("id")).uniqueResult();
	}

	public void update(Process process) {
		MySQL.currentSession().update(process);
	}
}
