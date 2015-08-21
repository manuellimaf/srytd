package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.errors.ErrorHandler.tryAndInform;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.daos.TagMappingDao;
import ar.com.dccsoft.srytd.model.Device;

public class TagMappingService {

	private static Logger logger = LoggerFactory.getLogger(TagMappingService.class);
	private TagMappingDao tagMappingDao = new TagMappingDao();

	public List<Device> getAllMappings() {
		return tryAndInform("Error reading tag mappings", () -> {
			logger.info("Reading tag mappings");
			List<Device> mappings = tagMappingDao.getAll();
			logger.info(String.format("%d tag mappings read", mappings.size()));
			return mappings;
		});
	}

}
