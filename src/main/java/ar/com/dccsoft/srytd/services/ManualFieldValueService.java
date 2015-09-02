package ar.com.dccsoft.srytd.services;

import static ar.com.dccsoft.srytd.utils.hibernate.Datasource.MySQL;
import static ar.com.dccsoft.srytd.utils.hibernate.TransactionManager.transactional;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.dccsoft.srytd.api.dto.Page;
import ar.com.dccsoft.srytd.daos.MappedFieldValueDao;
import ar.com.dccsoft.srytd.model.MappedFieldValue;

public class ManualFieldValueService {

	private static Logger logger = LoggerFactory.getLogger(ManualFieldValueService.class);
	private MappedFieldValueDao dao = new MappedFieldValueDao();

	public Page getPage(Integer start, Integer limit) {
		return transactional(MySQL, (session) -> {
			List<MappedFieldValue> elems = dao.getPageForManualValues(start, limit);
			Long total = dao.countAllManualValues();
			return new Page(elems, total);
		});
	}

}
