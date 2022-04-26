/**
 * A Connect-4 player that makes random valid moves.
 * 
 * @author Daniel Szafir
 * 			Madeline Sinkovic
 *
 */
public class MinimaxPlayer implements Player
{
   int id;
   int oppId;
   int cols;

    @Override
    public String name() {
        return "Minnie Maximus";
    }

    @Override
    public void init(int id, int msecPerMove, int rows, int cols) {
    	this.id = id;
    	this.cols = cols;
    	oppId = 3 - id;
    }

    @Override
    public void calcMove(
        Connect4Board board, int oppMoveCol, Arbitrator arb) 
        throws TimeUpException {
       
    	// Make sure there is room to make a move.
        if (board.isFull()) {
            throw new Error ("Complaint: The board is full!");
        }
            int move = 0;
            int maxDepth = 1;
            int score;
            
            while(!arb.isTimeUp() && maxDepth <= board.numEmptyCells()) {
            	
            int bestScore = -1000;

            for(int col = 0; col < 7; col++) {
            	if(board.isValidMove(col)) {
            		board.move(col, id);
            		
            		score = minimax(board, maxDepth - 1, false, arb);
            		
            			if(score > bestScore) {
            				
            				bestScore = score;
            				move = col;
            			}
            		
            		board.unmove(col, id);
            	}
            }
            arb.setMove(move);
            maxDepth++;
           
        }
    }
    
    
    public int minimax(Connect4Board board, int depth, boolean isMaximizing, Arbitrator arb) {
    	
    	
    	if (depth == 0 || board.numEmptyCells() == 0 || arb.isTimeUp()) {
    		
    		return score(board);
    	}
    	
    	if(isMaximizing) {
    		
    		int bestScore = -1000;
    		
    		for(int col = 0; col < 7; col++) {
            	if(board.isValidMove(col)) {
            		board.move(col, id);
            		
            		bestScore = Math.max(bestScore, minimax(board, depth - 1, false, arb));
            		board.unmove(col, id);
    	}
    		
    }
    		return bestScore;
    	}
    
    	else {
    		
    		int bestScore = 1000;
    		
    		for(int col = 0; col < 7; col++) {
            	if(board.isValidMove(col)) {
            		board.move(col, oppId);
            		
            		bestScore = Math.min(bestScore, minimax(board, depth - 1, true, arb));
            		board.unmove(col, oppId);
    	}
    		}
    		return bestScore;
    		
    	}
    }
    
    public int score(Connect4Board board) {
    	
    	
    	return calcScore(board, id) - calcScore(board, oppId);
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
