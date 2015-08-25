package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.DeviceDao;
import ar.com.dccsoft.srytd.model.Device;

public class DeviceService {

	private static Logger logger = LoggerFactory.getLogger(DeviceService.class);
	private DeviceDao deviceDao = new DeviceDao();

	public List<Device> getAllDevices() {
		return tryAndInform("Error reading devices", () -> {
			logger.info("Reading devices");
			List<Device> devices = deviceDao.getAll();
			logger.info(String.format("%d devices found", devices.size()));
			return devices;
		});
	}

}
