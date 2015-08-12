package ar.com.dccsoft.srytd.services;

import java.util.Arrays;
import java.util.List;

import ar.com.dccsoft.srytd.daos.AppPropertyDao;
import ar.com.dccsoft.srytd.model.AppProperty;

import com.google.common.collect.Lists;

public class AppPropertyService {

	private static final String ALERTS_RECIPIENTS = "alerts_recipients";
	private static final String COMPANY_ID = "company_id";
	private static final String FACILITY_ID = "facility_id";
	private AppPropertyDao dao = new AppPropertyDao();

	public List<String> getAlertsRecipients() {
		AppProperty prop = dao.getProperty(ALERTS_RECIPIENTS);
		if (prop != null) {
			return Arrays.asList(prop.getValue().split(","));
		}
		return Lists.newArrayList();
	}

	public String getCompanyId() {
		return dao.getProperty(COMPANY_ID).getValue();
	}

	public String getFacilityId() {
		return dao.getProperty(FACILITY_ID).getValue();
	}
}
