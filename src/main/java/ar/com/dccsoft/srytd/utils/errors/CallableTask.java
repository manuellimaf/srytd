package ar.com.dccsoft.srytd.utils.errors;

/**
 * This is a functional interface, required to build lambda expressions for java
 * @see https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
public interface CallableTask<A> {
	public A call();
}