package ar.com.dccsoft.srytd.daos;

import static ar.com.dccsoft.srytd.utils.TransactionManager.transactional;
import static ar.com.dccsoft.srytd.utils.Datasource.MySQL;
import ar.com.dccsoft.srytd.model.ProcessError;

public class ProcessErrorDao {

	public ProcessError saveError(Long errorId, String message, String username, String stacktrace) {
		return transactional(MySQL, (session) -> {
			ProcessError error = new ProcessError();
			error.setErrorId(errorId.toString());
			error.setMessage(message);
			error.setStacktrace(stacktrace);
			error.setUsername(username);
			return error;
		});
		
	}

}
