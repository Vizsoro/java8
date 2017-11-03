package stockOperations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LongPosition {
	
	
	public static LongTransaction optimalLongTransaction(int[] a){
		List<Deque<PriceAndDay>> transactions = new ArrayList<>();
		
		for(int index = 0; index < a.length; index++){
			int actualPrice = a[index];
			int actualDay = index+1;
			
			boolean newDeque = true;
			for(Deque<PriceAndDay> transaction : transactions){
				if(actualPrice > transaction.peekFirst().price){
					transaction.push(new PriceAndDay(actualPrice, actualDay));
					newDeque = false;
				}
				if(newDeque){
					newDeque = newDeque && actualPrice < transaction.peekLast().price;
				}
			}
			
			if(newDeque){
				Deque<PriceAndDay> deque = new ArrayDeque<>();
				deque.push(new PriceAndDay(actualPrice, actualDay));
				transactions.add(deque);
			}
			
		}
		
		Deque<PriceAndDay> optimal = transactions.stream()
					.sorted(
						(d1,d2)->(d1.peekFirst().price - d1.peekLast().price) - (d2.peekFirst().price - d2.peekLast().price))
					.findFirst().get();
		
		return new LongTransaction(optimal.peekLast().day,
									optimal.peekLast().price,
									optimal.peekFirst().day,
									optimal.peekFirst().price);
	}
	
	
	static class PriceAndDay{
		public final int price;
		public final int day;
		
		public PriceAndDay(int price, int day){
			this.price = price;
			this.day = day;
		}
		
	}
	
	static class LongTransaction{
		public final int startDay;
		public final int startPrice;
		public final int endDay;
		public final int endPrice;
		public final int profit;
		
		public LongTransaction(int startDay, int startPrice, int endDay, int endPrice){
			this.startDay = startDay;
			this.startPrice = startPrice;
			this.endDay = endDay;
			this.endPrice = endPrice;
			this.profit = endPrice - startPrice;
		}
		
	}

}
