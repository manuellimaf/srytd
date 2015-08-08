package ar.com.dccsoft.srytd.utils;

import org.hibernate.Session;

public interface HibernateSessionTask<A> {
    public A call(Session s);
}