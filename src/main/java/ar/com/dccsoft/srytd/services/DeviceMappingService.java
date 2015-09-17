package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;
import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.MappingDTO;
import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.daos.DeviceMappingDao;
import ar.com.dccsoft.srytd.model.DeviceMapping;

public class DeviceMappingService {

	private static Logger logger = LoggerFactory.getLogger(DeviceMappingService.class);
	private DeviceMappingDao dao = new DeviceMappingDao();

	public List<DeviceMapping> getAllDeviceMappings() {
		return tryAndInform("Error reading device mappings", () -> {
			logger.info("Reading device mappings");
			List<DeviceMapping> deviceMappings = transactional(MySQL, (session) -> dao.getAll());
			logger.info(String.format("%d device mappings found", deviceMappings.size()));
			return deviceMappings;
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

	public void createMapping(MappingDTO dto, String username) {
		DeviceMapping device = new DeviceMapping();
		try {
			BeanUtils.copyProperties(device, dto);
		} catch (Exception e) {
			throw new RuntimeException("Error creating device mapping", e);
		}
		device.setCreatedBy(username);
		device.setCreationDate(new Date());
		transactional(MySQL, (session) -> {
			dao.save(device);
			return null;
		});
	}

	public void updateMapping(MappingDTO dto) {
		transactional(MySQL, (session) -> {
			DeviceMapping device = dao.findDeviceMapping(dto.getId());
			try {
				BeanUtils.copyProperties(device, dto);
			} catch (Exception e) {
				throw new RuntimeException("Error updating device mapping", e);
			}
			dao.update(device);
			return null;
		});
		
	}

	public Boolean existsMappingForDevice(String name) {
		return transactional(MySQL, (session) -> dao.findByDeviceName(name) != null);
	}

	public Boolean isValidDevice(Long id, String name) {
		return transactional(MySQL, (session) -> {
			DeviceMapping device = dao.findDeviceMapping(id);
			DeviceMapping d = dao.findByDeviceName(name);
			return device.getId().equals(d.getId());
		});
	}

	public DeviceMapping getMappingForDevice(String name) {
		return transactional(MySQL, (session) -> dao.findByDeviceName(name));
	}
	
	public Boolean existsDevice(Long id) {
		return transactional(MySQL, (session) -> dao.findDeviceMapping(id) != null);
	}

}
