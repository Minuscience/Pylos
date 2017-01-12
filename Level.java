
public class Level {
	//classe de niveau 
	//il y a 4 niveau de 0 (le sommet) a 3
	private Ball[][] grid;

	public Level(int lenght) {
		grid = new Ball[lenght][lenght];
	}

	public Ball[][] getGrid() {
		return grid;
	}

	public void setGrid(Ball[][] grid) {
		this.grid = grid;
	}
	
	public Ball getBall(int i,int j){
		return grid[i][j];
	}
	
	public void setBall(Ball ball, int x, int y){
		grid[x][y]= ball;
	}
	
	public String toString() {
		StringBuffer map = new StringBuffer();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j] == null)
					map.append(".");
				else
					map.append(grid[i][j]);
			}
			map.append("\n");
		}
		return map.toString();
	}
	 
	
	/**
	 * Check if a case is empty or not
	 * @param i
	 * @param j
	 * @return true if the case is empty
	 */
	public boolean caseIsEmpty(int i,int j){
		return grid[i][j] == null;
	}
	
	
}
