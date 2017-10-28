package forkJoinExample;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static volatile ConcurrentHashMap<Integer, Integer> fiboCache = new ConcurrentHashMap<>();
	private int fib;

	public Fibonacci(int n) {
		this.fib = n;
	}

	@Override
	protected Integer compute() {

		if (fib == 1 || fib == 2) {
			return 1;
		}

		if (fiboCache.containsKey(fib)) {
			return fiboCache.get(fib);
		}

		Fibonacci fib1 = new Fibonacci(fib - 1);
		fib1.fork();
		Fibonacci fib2 = new Fibonacci(fib - 2);
		int result = fib2.compute() + fib1.join();
		fiboCache.putIfAbsent(fib, result);
		return result;
	}

	public static void main(String... args) {
		ForkJoinPool fjPool = new ForkJoinPool();
		Fibonacci fib = new Fibonacci(30);
		fjPool.execute(fib);
		int result = fjPool.invoke(fib);
		System.out.println(result);

	}

}
