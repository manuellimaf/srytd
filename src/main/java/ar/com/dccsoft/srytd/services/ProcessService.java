package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.util.Date;

import ar.com.dccsoft.srytd.daos.ProcessDao;

public class ProcessService {

	private ProcessDao processDao = new ProcessDao();

	public Long create(Date from, String username) {
		String errorMessage = String.format("Error creating process for date %tc", from);
		return tryAndInform(errorMessage, () -> {
			return processDao.create(from, username).getId();
		});
	}

}
