import static java.lang.Math.abs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Dynamic {

	int k;
	int noOfItems;
	public static ArrayList<Integer> dataItems;
	public static ArrayList<Integer> cz;
	public static ArrayList<Integer> oldCz;
	public static ArrayList<Integer> row;
	public static ArrayList<ArrayList<Integer>> groups;
	public static Scanner input;
	public static ArrayList<Integer> arr;	
	public static String mem;

	static int counter = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		arr = new ArrayList<Integer>();
		Runnable r = new Runnable() 
		{
			public void run() 
			{
				while (counter < 100000) 
				{
					int abc = (int) (Math.random() * 200); // generating random numbers on a runnable thread
					arr.add(abc);
					counter++;
				}
				System.out.println(arr.toArray().toString());
			}
		};

		new Thread(r).start(); // starting the thread of random numbers generation
								

		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException ex) 
		{
			System.out.println("Sleep exception");
		}

		for(int p=25;p<1001;p=p+25)
		   bucketAlgo(p, arr);
		

	}

	public static boolean bucketAlgo(int size, ArrayList<Integer> arr1) {
		Runtime rt = Runtime.getRuntime();
		long rem;
		long total = rt.totalMemory();
		long startTime = System.nanoTime();

		int ind = 0;
		

		ArrayList<Integer> bucket0 = new ArrayList<Integer>();
		ArrayList<Integer> bucket1 = new ArrayList<Integer>();

		/********* Implementing the bucket algorithm *************/
		while ((arr1.size() - ind)>5 ) {

			try
			{
			bucket0 = new ArrayList<>();
			bucket1 = new ArrayList<>();
			bucket0.add(arr1.get(ind)); //[0] = arr1.get(ind);

			
			ind++;
			for (int i = 0; i < size - 1; i++) {
				bucket0.add(arr1.get(ind));// [i + 1] = arr1.get(ind);
				bucket1.add(arr1.get(ind));// [i] = arr1.get(ind);
				ind++;
			}
			bucket1.add(arr1.get(ind));// [size - 1] = arr1.get(ind);
			ind++;

			kmeans(2, bucket0.size(), bucket0);

	
			kmeans(2, bucket1.size(), bucket1);
			}
			catch(Exception ex)
			{
				
			}
		}
		long endTime = System.nanoTime();
		long timeTaken = endTime - startTime;
		long free = rt.freeMemory();

		rem = total - free;

		System.out.println("################################### TIME :" + ((timeTaken)));

		System.out.println("################################### MEMORY :" + (rem / 1024));
		
		return true;
	}

	private static void kmeans(int k, int noOfItems, ArrayList<Integer> bucket) {
		// TODO Auto-generated method stub
		dataItems = new ArrayList<Integer>();
		cz = new ArrayList<Integer>();

		oldCz = new ArrayList<Integer>();
		row = new ArrayList<Integer>();
		groups = new ArrayList<ArrayList<Integer>>();
		input = new Scanner(System.in);

		for (int i = 0; i < k; i++) 
		{
			groups.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < noOfItems; i++) 
		{
			dataItems.add(bucket.get(i));
			if (i < k) 
			{
				cz.add(bucket.get(i));
			}
		}

		int iter = 1;
		do {
			for (int aItem : dataItems) {
				for (int c : cz) 
				{
					row.add(abs(c - aItem));
				}
				groups.get(row.indexOf(Collections.min(row))).add(aItem);
				row.removeAll(row);
			}
			for (int i = 0; i < k; i++) {
				if (iter == 1) {
					oldCz.add(cz.get(i));
				} else {
					oldCz.set(i, cz.get(i));
				}
				if (!groups.get(i).isEmpty()) {
					cz.set(i, average(groups.get(i)));
				}
			}
			if (!cz.equals(oldCz)) {
				for (int i = 0; i < groups.size(); i++) {
					groups.get(i).removeAll(groups.get(i));
				}
			}
			iter++;
		} while (!cz.equals(oldCz));
	
	}

	public static int average(ArrayList<Integer> list) 
	{
		int sum = 0;
		for (Integer value : list) 
		{
			sum = sum + value;
		}
		return sum / list.size();
	}

}