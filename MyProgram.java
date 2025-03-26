public class MyProgram {
	public static void main(String[] args) {
		int[][] array2D = new int[2][];
		int[][] array2D_1 = {{4,5,2},
								{1,3},
								{7,1,5,6}};
		array2D[0] = new int[2]; 
		array2D[0][0] = 7;
		array2D[0][1] = 2; 
		
		array2D[1] = new int[3]; 
		array2D[1][0] = 1;
		array2D[1][1] = 3;
		array2D[1][2] = 8;

		for(int i = 0; i < array2D.length; i++) { 
			for(int j = 0; j < array2D[i].length; j++) {
				System.out.print(array2D[i][j] + " ");
			}
			System.out.println();
		}

		for(int[] x : array2D_1) { for(int y : x) {
			System.out.print(y + " ");
			}
			System.out.println();
		}
	}
}