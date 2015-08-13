package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.Date;

import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessStatus;

public class ProcessDao {

	public Process create(Date from, String username) {
		return transactional(MySQL, (session) -> {
			Process p = new Process();
			p.setStartDate(new Date());
			p.setStartedBy(username);
			p.setReadingsFrom(from);
			p.setStatus(ProcessStatus.STARTED);
			session.save(p);
			return p;
		});
	}

	public void saveFile(Long processId, String file) {
		transactional(MySQL, (session) -> {
			Process p = find(processId);
			p.setFile(file);
			session.update(p);
			return null;
		});

	}

	public Process find(Long id) {
		return transactional(MySQL, (session) -> {
			return (Process) session.get(Process.class, id);
		});
	}
}
