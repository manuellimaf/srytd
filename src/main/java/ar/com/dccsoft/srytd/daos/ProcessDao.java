package ar.com.dccsoft.srytd.daos;

import java.util.Date;

import ar.com.dccsoft.srytd.model.Process;
import ar.com.dccsoft.srytd.model.ProcessStatus;
import static ar.com.dccsoft.srytd.utils.TransactionManager.transactional;

public class ProcessDao {

	public Process create(Date from, String username) {
    	return transactional((session) -> {
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

