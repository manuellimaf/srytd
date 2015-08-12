package ar.com.dccsoft.srytd.services;

import java.util.Arrays;
import java.util.List;

import ar.com.dccsoft.srytd.daos.AppPropertyDao;
import ar.com.dccsoft.srytd.model.AppProperty;

import com.google.common.collect.Lists;

public class AppPropertyService {

	private AppPropertyDao dao = new AppPropertyDao();

	public List<String> getAlertsRecipients() {
		AppProperty prop = dao.getProperty("alerts_recipients");
		if (prop != null) {
			return Arrays.asList(prop.getValue().split(","));
		}
		return Lists.newArrayList();
	}
}
