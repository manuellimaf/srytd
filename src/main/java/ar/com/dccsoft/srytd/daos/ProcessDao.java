package ar.com.dccsoft.srytd.daos;

import java.util.Date;

import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

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

	
}

