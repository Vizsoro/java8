package forkJoinExample;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmstrongNumber extends RecursiveTask<List<Integer>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int from;
	private int to;
	
	public AmstrongNumber(int from, int to){
		this.from = from;
		this.to = to;
	}
	
	@Override
	protected List<Integer> compute() {
		if(to-from > 10){
			int mid = (to-from)/2 + from;
			AmstrongNumber aN1 = new AmstrongNumber(from, mid);
			AmstrongNumber aN2 = new AmstrongNumber(mid+1, to);
			aN2.fork();
			List<Integer> answer = aN1.compute();
			answer.addAll(aN2.join());
			return answer;
		} else {
			return findAmstrongNumbers();
		}
	}

	private List<Integer> findAmstrongNumbers() {

		return IntStream.rangeClosed(from, to).filter(AmstrongNumber::check).boxed().collect(Collectors.toList());
	}
	
	private static boolean check(int num){
		
		String numAsString = String.valueOf(num);
		int sum = 0;
		int size = numAsString.length();
		for(int place = 0; place < size; place++){
			sum += Math.pow(Double.valueOf(numAsString.substring(place, place+1)), size);
		}
		return sum == num;
	}
	
	public static void main(String[] args){
		AmstrongNumber amNum = new AmstrongNumber(1, 500000);
		ForkJoinPool fjp = new ForkJoinPool();
		fjp.invoke(amNum).forEach(System.out::println);
		
	}
	

}
