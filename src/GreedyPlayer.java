/**
 * A Connect-4 player that makes random valid moves.
 * 
 * @author Daniel Szafir
 * 			Madeline Sinkovic
 *
 */
public class GreedyPlayer implements Player
{
   int id;

    @Override
    public String name() {
        return "Greedy Boy";
    }

    @Override
    public void init(int id, int msecPerMove, int rows, int cols) {
    	this.id = id;
    }

    @Override
    public void calcMove(
        Connect4Board board, int oppMoveCol, Arbitrator arb) 
        throws TimeUpException {
       
    	// Make sure there is room to make a move.
        if (board.isFull()) {
            throw new Error ("Complaint: The board is full!");
        }
            int maxScore = -1;
            
            
            for(int col = 0; col < 7; col++) {
            	if(board.isValidMove(col)) {
            		board.move(col, id);
            		
            		if(calcScore(board, id) > maxScore) {
            			arb.setMove(col);
            			maxScore = calcScore(board, id);
            		}
            		board.unmove(col, id);
            	}
            }
        }
        
       
        
        public int calcScore(Connect4Board board, int id) {
        	
        	final int cols = board.numCols();
        	final int rows = board.numRows();
        	
        	int score = 0;
        	
        	//vertical check
        	for(int c = 0; c < cols; c++) {
        		for(int r = 0; r <= rows - 4; r++) {
        			if(board.get(r + 0, c) != id) continue;
        			if(board.get(r + 1, c) != id) continue;
        			if(board.get(r + 2, c) != id) continue;
        			if(board.get(r + 3, c) != id) continue;
        			
        			score ++;
        		}
        	}
        	//horizontal check
        	for(int r = 0; r < rows; r++) {
        		for(int c = 0; c <= cols - 4; c++) {
        			if(board.get(r, c + 0) != id) continue;
        			if(board.get(r, c + 1) != id) continue;
        			if(board.get(r, c + 2) != id) continue;
        			if(board.get(r, c + 3) != id) continue;
        			
        			score ++;
        		}
        	}
        	//diagonal check
        	for(int c = 0; c <= cols - 4; c++) {
        		for(int r = 0; r <= rows - 4; r++) {
        			if(board.get(r + 0, c + 0) != id) continue;
        			if(board.get(r + 1, c + 1) != id) continue;
        			if(board.get(r + 2, c + 2) != id) continue;
        			if(board.get(r + 3, c + 3) != id) continue;
        			
        			score ++;
        		}
        }
        	for(int c = 0; c <= cols - 4; c++) {
        		for(int r = rows - 1; r >= 4 - 1; r--) {
        			if(board.get(r - 0, c + 0) != id) continue;
        			if(board.get(r - 1, c + 1) != id) continue;
        			if(board.get(r - 2, c + 2) != id) continue;
        			if(board.get(r - 3, c + 3) != id) continue;
        			
        			score ++;
        		}
        }	
        return score;	
    }
}