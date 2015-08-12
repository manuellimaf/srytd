package ar.com.dccsoft.srytd.utils.hibernate;

import org.hibernate.Session;

public interface HibernateSessionTask<A> {
    public A call(Session s);
}