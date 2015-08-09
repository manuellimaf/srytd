package ar.com.dccsoft.srytd.utils;

import org.hibernate.Session;

public enum Datasource {
	SQLSERVER {
		@Override
		public Session currentSession() {
			return HibernateUtil.currentSQLServerSession();
		}
	}, 
	MySQL {
		@Override
		public Session currentSession() {
			return HibernateUtil.currentMySQLSession();
		}
	};	
	
	public abstract Session currentSession();
}

