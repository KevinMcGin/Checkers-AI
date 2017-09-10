import java.util.ArrayList;

public class CheckersMoveScore implements Comparable<CheckersMoveScore>
{
	byte playerMove;
	byte playerNextMove;
	float score;
	CheckersMove move;
	CheckersData board;
	ArrayList<CheckersMoveScore> moves;
	boolean goalState = false;
	CheckersMoveScore(float score,CheckersMove move,CheckersData board,byte playerMove,byte playerNextMove)
	{
		this.playerMove = playerMove;
		this.playerNextMove = playerNextMove;
		this.score = score;
		this.move = new CheckersMove(move);
		this.board = new CheckersData(board);
		
		int RedPieces=0; int BlackPieces = 0;
		for(int i = 0;i < 8;i++)
		{
			for(int j = 0;j < 8;j++)
			{
				if(board.board.Get(i, j) == CheckersData.RED)
				{
					RedPieces++;
					if(BlackPieces != 0)
					{
						break;
					}
				}
				else if(board.board.Get(i, j) == CheckersData.BLACK)
				{
					BlackPieces++;
					if(RedPieces != 0)
					{
						break;
					}
				}
			}
			if(RedPieces != 0 && BlackPieces != 0)
			{
				break;
			}
		}
		if(RedPieces == 0 && BlackPieces == 0)
		{
			goalState = true;
		}
		else
		{
			CheckersMove[] moves = board.getLegalMoves(playerNextMove);
			if(moves == null)
			{
				goalState = true;
			}
		}
	}
	void IncreaseDepth(ArrayList<CheckersMoveScore> moves)
	{
		this.moves = new ArrayList<CheckersMoveScore>(moves);		
	}
	void UpdateScore(float score)
	{
		this.score = score;
	}
	public int compareTo(CheckersMoveScore other)
	{
		if(playerMove == other.playerMove)
			if(playerMove == CheckersData.RED)
				return (int)((score-other.score)*10000);
			else
				return (int)((other.score-score)*10000);
		else
			if(playerMove == CheckersData.RED)
				return -1000000000;
			else
				return 1000000000;
	}
}

