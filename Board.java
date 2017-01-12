
public class Board {
	private Level [] board ;
	
	public Board(){
		board = new Level[4];
		for (int i = 0; i < board.length; i++) {
			board[i]  = new Level(i+1);
		}
	}
	
	// affiche chacun des niveaux
	public String toString(){
		StringBuffer plate = new StringBuffer();
		for (int i = 0; i < board.length; i++) {
			plate.append(board[i].toString());
			plate.append("\n");
		}
		return plate.toString();
	}
	
	//retourne une balle sur le plateau
	public Ball getBall(int lv,int x,int y){
		return board[lv].getBall(x, y);
	}
	
	//verrifie si on a une balle au sommet
	public boolean onTop(){
		return this.getBall(0, 0, 0) != null ;
	}
	
	//verifie si une case est vide
	public boolean isEmpty(int lv, int x, int y){
		return getBall(lv, x, y)==null;
	}
	
	//verifie si on peut jouer sur une case
	public boolean playableCase(int lv, int x, int y){
		if(lv < 0 || lv > 3)
			return false;
		if(isEmpty(lv, x, y)){
			if(lv==3)
				return true;
			else if(!isEmpty(lv+1, x, y) && !isEmpty(lv+1, x+1, y) && !isEmpty(lv+1, x, y+1) && !isEmpty(lv+1, x+1, y+1))
				return true;
			else 
				return false;
		}
		return false;
	}
	
	//place la boule ball sur le plateau
	public boolean setBall(Ball ball, int lv, int x, int y){
		if(isEmpty(lv, x, y)){
			
			board[lv].setBall(ball, x, y);
			return true;
		}
		return false;
	}
	
}
