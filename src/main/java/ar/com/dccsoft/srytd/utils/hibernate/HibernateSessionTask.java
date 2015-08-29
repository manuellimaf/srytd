package ar.com.dccsoft.srytd.utils.hibernate;

import org.hibernate.Session;

/**
 * This is a functional interface, required to build lambda expressions for java.
 * Method call receives one paremeter that will be available for every implementation.
 * @see https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */

public interface HibernateSessionTask<A> {
    public A call(Session s);
}