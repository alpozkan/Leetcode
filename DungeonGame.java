package LeetCode.Hard;

// Completed.
// find the minimum health requirement to be able to reach the end in a dungeon board.


public class DungeonGame {
	
	
	
	
	public int calculateMinimumHP(int[][] dungeon) {
		
		if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
		int rowCount = dungeon.length;
		int colCount = dungeon[0].length;
        int [][] MinHealth = new int [rowCount][colCount];
        
        int LastColInex = colCount - 1;
        int LastRowIndex = rowCount - 1;
        MinHealth[LastRowIndex][LastColInex] = - dungeon[LastRowIndex][LastColInex];
        for(int rowIndex = LastRowIndex - 1; rowIndex >= 0; rowIndex--)
        {
        	int DownRequirement = - dungeon[rowIndex][LastColInex] + MinHealth[rowIndex + 1][LastColInex];
        	int CurrentRequirement = - dungeon[rowIndex][LastColInex];
        	MinHealth[rowIndex][LastColInex] = Math.max(DownRequirement, CurrentRequirement);
        }
        
        for(int colIndex = LastColInex - 1; colIndex >= 0; colIndex--)
        {
        	int RightRequirement = - dungeon[LastRowIndex][colIndex] + MinHealth[LastRowIndex][colIndex+1];
        	int CurrentRequirement = - dungeon[LastRowIndex][colIndex];
        	MinHealth[LastRowIndex][colIndex] = Math.max(RightRequirement, CurrentRequirement) ;
        	
        }
        
        for(int rowIndex = LastRowIndex - 1; rowIndex >= 0; rowIndex--)
        {
        	for(int colIndex = LastColInex - 1; colIndex >= 0; colIndex--)
        	{
        		int DownRequirement =  - dungeon[rowIndex][colIndex] + MinHealth[rowIndex + 1][colIndex];
        		int RightRequirement = - dungeon[rowIndex][colIndex] +  MinHealth[rowIndex][colIndex+1];
        		int LeastRequirement = Math.min(DownRequirement, RightRequirement);
        		int CurrentRequirement = - dungeon[rowIndex][colIndex];
        		MinHealth[rowIndex][colIndex] = Math.max(LeastRequirement, CurrentRequirement);
        	}
        }
        return Math.max(MinHealth[0][0] + 1, 1) ;
    }
}
