package ar.com.dccsoft.srytd.utils;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionManager {

    public static <A> A transactional(HibernateSessionTask<A> task) {
		Session s = HibernateUtil.currentSession();
		Transaction tx = s.beginTransaction();
		A res = null;
		try {
			res = task.call(s);
			tx.commit();
		} catch (Throwable t) {
			tx.rollback();
			throw new RuntimeException("Error in transaction", t);
		}
		return res;
    }

}


