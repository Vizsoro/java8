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
	
	@Test
	public void optimumAfterBigFall(){
		int[] input = {10,22,23,7,10,15,22,20};
		LongTransaction transaction = LongPosition.optimalLongTransaction(input);
		assertTrue(transaction.startDay == 4);
		assertTrue(transaction.startPrice == 7);
		assertTrue(transaction.endPrice == 22);
		assertTrue(transaction.endDay == 7);
	}
	
	@Test
	public void twoEqualWaves(){
		int[] input = {10,12,14,16,10,12,14,16};
		LongTransaction transaction = LongPosition.optimalLongTransaction(input);
		assertTrue(transaction.startDay == 1);
		assertTrue(transaction.startPrice == 10);
		assertTrue(transaction.endPrice == 16);
		assertTrue(transaction.endDay == 4);
	}
	
	@Test
	public void smallPikeAfterLongFall(){
		int[] input = {40,30,20,10,9,8,10,12};
		LongTransaction transaction = LongPosition.optimalLongTransaction(input);
		assertTrue(transaction.startDay == 6);
		assertTrue(transaction.startPrice == 8);
		assertTrue(transaction.endPrice == 12);
		assertTrue(transaction.endDay == 8);
	}
	
	@Test
	public void smallPikeAfterStablePeriod(){
		int[] input = {10,10,10,10,8,10,12, 12, 12};
		LongTransaction transaction = LongPosition.optimalLongTransaction(input);
		assertTrue(transaction.startDay == 5);
		assertTrue(transaction.startPrice == 8);
		assertTrue(transaction.endPrice == 12);
		assertTrue(transaction.endDay == 7);
	}
	
}
