package stockOperations;

import org.junit.Test;

import stockOperations.LongPosition;
import stockOperations.LongPosition.LongTransaction;

import static org.junit.Assert.*;

public class LongPositionTest {

	
	@Test
	public void monotonGrowing(){
		int[] input = {10,12,14,17,20,20,22};
		LongTransaction transaction = LongPosition.optimalLongTransaction(input);
		assertTrue(transaction.startDay == 1);
		assertTrue(transaction.startPrice == 10);
		assertTrue(transaction.endPrice == 22);
		assertTrue(transaction.endDay == 7);
		assertTrue(transaction.profit == 12);
	}
	
	@Test
	public void onePeekTheanMonotonShrinking(){
		int[] input = {10,22,14,13,12,11,10};
		LongTransaction transaction = LongPosition.optimalLongTransaction(input);
		assertTrue(transaction.startDay == 1);
		assertTrue(transaction.startPrice == 10);
		assertTrue(transaction.endPrice == 22);
		assertTrue(transaction.endDay == 2);
	}
	
}
