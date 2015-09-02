package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import ar.com.dccsoft.srytd.model.ProcessAlert;

public class ProcessAlertDao {

	public void saveAlert(ProcessAlert alert) {
		MySQL.currentSession().save(alert);
	}

	public ProcessAlert find(Long id) {
		return (ProcessAlert) MySQL.currentSession().get(ProcessAlert.class, id);
	}

}
