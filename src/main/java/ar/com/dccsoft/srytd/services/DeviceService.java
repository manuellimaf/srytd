package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.daos.DeviceDao;
import ar.com.dccsoft.srytd.model.Device;

public class DeviceService {

	private static Logger logger = LoggerFactory.getLogger(DeviceService.class);
	private DeviceDao dao = new DeviceDao();

	public List<Device> getAllDevices() {
		return tryAndInform("Error reading devices", () -> {
			logger.info("Reading devices");
			List<Device> devices = transactional(MySQL, (session) -> dao.getAll());
			logger.info(String.format("%d devices found", devices.size()));
			return devices;
		});
	}

	public Page getPage(Integer start, Integer limit) {
		return transactional(MySQL, (session) -> {
			return new Page(dao.getPage(start, limit), dao.countAll());
		});
	}

	public void deleteMapping(Long id) {
		transactional(MySQL, (session) -> {
			dao.delete(id);
			return null;
		});
	}

}
