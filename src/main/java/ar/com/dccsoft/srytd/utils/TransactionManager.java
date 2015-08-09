package ar.com.dccsoft.srytd.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager {

    public static <A> A transactional(Datasource datasource, HibernateSessionTask<A> task) {
		Session s = datasource.currentSession();
		Transaction tx = s.beginTransaction();
		try {
			A result = task.call(s);
			tx.commit();
			return result;
		} catch (Throwable t) {
			tx.rollback();
			throw new RuntimeException("Error in transaction", t);
		}
    }

}

