package com.demo.threadpoolsize;

import java.util.List;

public class CPUTypeTest implements Runnable {

	//整体执行时间，包括在队列中等待的时间
	List<Long> wholeTimeList;
	//真正执行时间
	List<Long> runTimeList;
	
	private long initStartTime = 0;
	
	/**
	 * 构造函数
	 * @param runTimeList
	 * @param wholeTimeList
	 */
	public CPUTypeTest(List<Long> runTimeList, List<Long> wholeTimeList) {
		initStartTime = System.currentTimeMillis();
		this.runTimeList = runTimeList;
		this.wholeTimeList = wholeTimeList;
	}
	
	/**
	 * 判断素数
	 * @param number
	 * @return
	 */
	public boolean isPrime(final int number) {
		if (number <= 1)
			return false;

		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * 計算素数
	 * @param number
	 * @return
	 */
	public int countPrimes(final int lower, final int upper) {
		int total = 0;
		for (int i = lower; i <= upper; i++) {
			if (isPrime(i))
				total++;
		}
		return total;
	}

	public void run() {
		long start = System.currentTimeMillis();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		countPrimes(1, 1000000);
		long end = System.currentTimeMillis();

		long wholeTime = end - initStartTime;
		long runTime = end - start;
		wholeTimeList.add(wholeTime);
		runTimeList.add(runTime);
		System.out.println("单个线程花费时间：" + (end - start));
	}

}
