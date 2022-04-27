package optimalBinarySearchTree;

public class optimalBinarySearchTree {
	double [][] result;
	double [] allPossibleResult;
	public char [] arrayKey ;
	public double [] arrayFreq;
	
	public optimalBinarySearchTree(char [] arrayKey, double [] arrayFreq){
		this.arrayFreq = arrayFreq;
		this.arrayKey = arrayKey;
	}
	
	public static void main(String [] args) {
		char [] key = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J'};
		double [] freq = {0.05, 0.15, 0.12, 0.08, 0.02, 0.18, 0.04, 0.07, 0.16, 0.13};
		optimalBinarySearchTree c = new optimalBinarySearchTree( key, freq);
		
		int arrayLen = c.arrayKey.length;
		System.out.println(arrayLen);
		
		c.result = c.getTable(c.arrayFreq, arrayLen);
		
		for (int i =0; i<c.result.length; i++) {
			for (int j=0; j<c.result[i].length; j++) {
				System.out.print(" "+ String.format("%,.4f", c.result[i][j])+" ");
			}
			System.out.println();
		}
	}
	
	public double [][] getTable(double [] freqArr, int length) {
		double [][] tableResult = new double [freqArr.length+1][freqArr.length+2];
		return constructTable(freqArr, length, 0, 0, tableResult, tableResult.length);
	}
	
	public double [][] constructTable(double [] freqArr, int length, int numOfNode, int numberOfLoop, double[][] tableResult, int numberOfInnerLoop) {
		if(numberOfLoop == freqArr.length+1) { // +1 is the final loop 
			return tableResult;
		}
		// recurrence indicate the size of the grid (loop depen on number of nodes include each time (fromA to AB to ABC to ABCD .... till the end)_
		int i = 0; // always start with (0,0)
		int j = 0;
		int h = i+1;
		int g = j+numOfNode;
	   // index g is depends number of node in side the binary tree
		// try weight every node inside the tree when they as root node 
		
		// numberOfInner loop decrease becasuse the number of nodes inside the tree increase (so calculate time decrease and number of weight per calculation increase 
		for(int k =0;k<numberOfInnerLoop; k++ ) { // the inner loop is moving the grid down (from AB to BC to CD 
			if(numOfNode==0) { // assign the zero inside the table 
				tableResult[i][j] = 0;
				i++;
				j++;
			}else {
				System.out.println("i: "+i + " j: "+j+" h: "+h+" g: "+g);
				// getTheMinValue has the loop becuase when the tree expense, 
				// the number of node will increase increase by one every time after 
				tableResult[i][g] = getTheMinValue(i,j,h,g,freqArr, tableResult, numOfNode); // this loop mean ( which is the smalles mean try them all, AB or BA, ABC or CBA or ACB ) 
				System.out.println("storedIndex is "+i+ " "+g);
				j++;
				h++;
				g++;
				i++;
			}
			System.out.println();
		}
		numberOfInnerLoop --;
		numOfNode++ ;
		numberOfLoop ++;
		System.out.println("Loop: "+numberOfLoop);
		return constructTable( freqArr, length, numOfNode, numberOfLoop, tableResult, numberOfInnerLoop);
	}
	
	 double getTheMinValue (int i,int j,int h,int g, double [] freqArr, double[][]tableResult, int numOfNode) {
		 double min = 1000000;
		 double sumOfWeight = 0;
		 int index = h-1;
		 for (int l=0; l<numOfNode; l++) {
			 sumOfWeight = sumOfWeight + freqArr[index];
			 index++;
		 }
		 System.out.println("Sum of weight "+ String.format("%,.4f",sumOfWeight ));
		 
		 for (int l=0; l<numOfNode; l++) {
			 System.out.println("i: "+i + " j: "+j+" h: "+h+" g: "+g);
			 double weight = tableResult[i][j] + tableResult[h][g] + sumOfWeight;
			 System.out.println("Add "+String.format("%,.2f", tableResult[i][j])+" "+String.format("%,.2f", tableResult[h][g])+ " "+String.format("%,.2f", sumOfWeight) );
			 System.out.println("the weight is "+String.format("%,.4f", weight ));
			 if (weight < min) {
				 min = weight;
			 }
			 j++;
			 h++;
			 
		 }
		 System.out.println("The min is "+ String.format("%,.4f",min ));
		 return  min;
	 }
	
}
