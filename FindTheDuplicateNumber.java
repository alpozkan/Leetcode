package LeetCode.Hard;

import java.util.Random;

// Completed.

public class FindTheDuplicateNumber {

	// nums contain only elements in range 1 to nums.length - 1.
	// exactly one duplicate exists, but it might be repeated several times.
	// Key observation: a range including the duplicate will show up more times than it is supposed to.

	public void test()
	{
		int size = 100;
		int Marray [] = new int [size+1];
		for(int i = 0; i < size; i++)
		{
			Marray[i] = i+1;
		}
		Random rand = new Random();
		int repeated = rand.nextInt(size);
		Marray[size] = repeated;
		System.out.println(repeated);
		System.out.println(findDuplicate(Marray));
	}
	
	
	
	public int findDuplicateWithin(int [] nums, int lowvalue, int highvalue)
	{
		if(lowvalue > highvalue) throw new RuntimeException();
		int mediumvalue = lowvalue + (highvalue - lowvalue)/2;
		int countlow = 0;
		int counthigh = 0;
		int countmedium = 0;
		// count the number of elements falling into any of the three categories.
		for(int i = 0; i < nums.length; i++)
		{
			if((nums[i] >= lowvalue) && (nums[i] <= highvalue))
			{
				if(nums[i] < mediumvalue)
					countlow++;
				else if(nums[i] > mediumvalue)
					counthigh++;
				else
					countmedium++;
			}
		}
		int result = -1;
		// search the part that has more elements than it was supposed to have.
		if(countmedium > 1) result = mediumvalue;
		else if (counthigh > (highvalue - mediumvalue))
			result = findDuplicateWithin(nums,mediumvalue+1,highvalue);
		else if(countlow > (mediumvalue - lowvalue))
			result = findDuplicateWithin(nums,lowvalue,mediumvalue - 1);
		else
			throw new RuntimeException();
		return result;
	}


	public int findDuplicate(int[] nums) {
		// assume nums is not null and contains at least two elements and exactly one duplicate value.
		int lowvalue = 1;
		int highvalue = nums.length - 1;
		return findDuplicateWithin(nums, lowvalue, highvalue);
	}
}
