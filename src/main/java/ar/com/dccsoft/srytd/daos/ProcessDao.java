package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

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
	public List<Process> getAll() {
		return MySQL.currentSession().createCriteria(Process.class).addOrder(Order.desc("id")).list();
	}

	public void update(Process process) {
		MySQL.currentSession().update(process);
	}
}
