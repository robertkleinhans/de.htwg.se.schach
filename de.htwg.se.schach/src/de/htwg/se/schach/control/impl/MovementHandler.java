/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach.control.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.htwg.se.schach.control.I.MovementHandlerInter;
import de.htwg.se.schach.control.I.PositionInter;
import de.htwg.se.schach.model.I.PawnInter;
import de.htwg.se.schach.model.I.PieceInter;
import de.htwg.se.schach.model.impl.*;

/**
 *
 * @author rob
 */
public class MovementHandler implements MovementHandlerInter {
	private static final  int MAXCOLUMN = 7;
	private static final  int MAXROW = 7;
	private Map<Position, Piece> figureHolder;

	public MovementHandler() {
		figureHolder = new HashMap<Position, Piece>();
		for (int i = 0; i <= MAXCOLUMN; i++) {
			figureHolder.put(new Position(1, i), new Pawn(1, i, 1, 0));
			figureHolder.put(new Position(6, i), new Pawn(6, i, -1, 1));

			if (i == 0 || i == 7) {
				figureHolder.put(new Position(0, i), new Rook(0, i, 0));
				figureHolder.put(new Position(7, i), new Rook(7, i, 1));
			} else if (i == 1 || i == 6) {
				figureHolder.put(new Position(0, i), new Knight(0, i, 0));
				figureHolder.put(new Position(7, i), new Knight(7, i, 1));
			} else if (i == 2 || i == 5) {
				figureHolder.put(new Position(0, i), new Bishop(0, i, 0));
				figureHolder.put(new Position(7, i), new Bishop(7, i, 1));
			} else if (i == 3) {
				figureHolder.put(new Position(0, i), new Queen(0, i, 0));
				figureHolder.put(new Position(7, i), new Queen(7, i, 1));
			}
			if (i == 4) {
				figureHolder.put(new Position(0, i), new King(0, i, 0));
				figureHolder.put(new Position(7, i), new King(7, i, 1));
			}
		}
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.MovementHandlerInter#getField()
	 */
	@Override
	public Map<Position, Piece> getField() {
		return this.figureHolder;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.MovementHandlerInter#movePiece(de.htwg.se.schach.control.impl.Position, de.htwg.se.schach.control.impl.Position, int)
	 */
	@Override
	public boolean movePiece(PositionInter start, Position end, int team) {
		Piece hold = figureHolder.get(start);
		if (hold == null || hold.getTeam() != team) {
			return false;
		}
		List<Position> move = getMovement(start);
		if (!move.contains(end)) {
			return false;
		}

		if (hold instanceof Pawn) {
			((PawnInter) hold).setFirstMove(false);
		}

		figureHolder.put(end, hold);
		figureHolder.remove(start);
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.MovementHandlerInter#checkPiece(de.htwg.se.schach.control.impl.Position, int)
	 */
	@Override
	public boolean checkPiece(PositionInter pos, int team) {
		if (!figureHolder.containsKey(pos)) {
			return false;
		} else {
			return figureHolder.get(pos).getTeam() == team;
		}
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.MovementHandlerInter#removePiece(de.htwg.se.schach.control.impl.Position)
	 */
	@Override
	public boolean removePiece(PositionInter pos) {
		if (!figureHolder.containsKey(pos)) {
			return false;
		}
		figureHolder.remove(pos);
		return true;
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.MovementHandlerInter#checkWin()
	 */
	@Override
	public int checkWin() {
		boolean team1 = false;
		boolean team0 = false;
		for (PieceInter pie : figureHolder.values()) {
			if (("KI").equals(pie.getName()) && pie.getTeam() == 1) {
				team1 = true;
			} else if (("KI").equals(pie.getName()) && pie.getTeam() == 0) {
				team0 = true;
			}
		}
		return winHelper(team1,team0);
	}
	
	int winHelper(Boolean a, Boolean b) {
		if (a && !b) {
			return 1;
		} else if (!a && b) {
			return 0;
		} else {
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see de.htwg.se.schach.control.impl.MovementHandlerInter#getMovement(de.htwg.se.schach.control.impl.Position)
	 */
	@Override
	public List<Position> getMovement(PositionInter pos) {
		PieceInter holder = figureHolder.get(pos);
		List<Position> ret = new LinkedList<Position>();

		if (holder == null) {
			return ret;
		}

		if (holder instanceof Bishop) {

			ret.addAll(getDiagonalMovement(pos));

		} else if (holder instanceof Knight) {

			ret.addAll(getKnightMovement(pos));

		} else if (holder instanceof Queen) {
			ret.addAll(getDiagonalMovement(pos));
			ret.addAll(getHorVerMovement(pos));

		} else if (holder instanceof Rook) {

			ret.addAll(getHorVerMovement(pos));

		} else if (holder instanceof King) {

			ret.addAll(getKingMovement(pos));

		} else {

			ret.addAll(getPawnMovement(pos));

		}

		return ret;
	}

	private List<Position> getPawnMovement(PositionInter pos) {
		int row = pos.getRow();
		int col = pos.getColumn();
		Pawn pawn = (Pawn) figureHolder.get(pos);
		List<Position> ret = new LinkedList<Position>();

		if (row + pawn.getDirection() >= 0 && row + pawn.getDirection() <= MAXROW) {
			Position tmp = new Position(row + pawn.getDirection(), col);

			PieceInter hold = figureHolder.get(tmp);
			if (hold == null) {
				ret.add(tmp);

				Position tmp2 = new Position(row + 2 * pawn.getDirection(), col);
				PieceInter hold2 = figureHolder.get(tmp2);
				if (pawn.isFirstMove() && hold2 == null) {
					ret.add(tmp2);
				}
			}

			Position tmpRight = new Position(row + pawn.getDirection(), col + 1);
			PieceInter holdRight = figureHolder.get(tmpRight);

			if (holdRight != null && holdRight.getTeam() != pawn.getTeam()) {
				ret.add(tmpRight);
			}

			Position tmpLeft = new Position(row + pawn.getDirection(), col - 1);
			PieceInter holdLeft = figureHolder.get(tmpLeft);

			if (holdLeft != null && holdLeft.getTeam() != pawn.getTeam()) {
				ret.add(tmpLeft);
			}
		}
		return ret;
	}

	List<Position> kingHelper(PositionInter pos, int varOne) {
		List<Position> ret = new LinkedList<Position>();
		int row = pos.getRow();
		int col = pos.getColumn();
		
		if(row + varOne <= MAXROW) {
			ret.add(new Position(row + varOne, col));
			if (col + varOne <= MAXCOLUMN) {
				ret.add(new Position(row + varOne, col + varOne));
				ret.add(new Position(row, col + varOne));
			}
			if (col - varOne >= 0) {
				ret.add(new Position(row + varOne, col - varOne));
				ret.add(new Position(row, col - varOne));
			}
		}
		
		
		return ret;
	}
	
	private List<Position> getKingMovement(PositionInter pos) {
		int row = pos.getRow();
		int col = pos.getColumn();
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();

		List<Position> retClean = new LinkedList<Position>();
		
		ret.addAll(kingHelper(pos,1));
		if (row - 1 >= 0) {
			ret.add(new Position(row - 1, col));
			if (col + 1 < 8) {
				ret.add(new Position(row - 1, col + 1));
				if (!ret.contains(new Position(row, col + 1))) {
					ret.add(new Position(row, col + 1));
				}
			}
			if (col - 1 >= 0) {
				ret.add(new Position(row - 1, col - 1));
				if (!ret.contains(new Position(row, col - 1))) {
					ret.add(new Position(row, col - 1));
				}
			}
		}

		for (Position posTmp : ret) {
			PieceInter hold = figureHolder.get(posTmp);

			if (!(hold != null && hold.getTeam() == team)) {
				retClean.add(posTmp);
			}
		}
		return retClean;
	}

	
	
	List<Position> getLeftUpper(PositionInter pos, int team) {
		int row = pos.getRow();
		int col = pos.getColumn();
		List<Position> ret = new LinkedList<Position>();
		
		for (int i = 1; i <= MAXROW; i++) {
			if (row - i >= 0 && col - i >= 0) {
				Position tmp = new Position(row - i, col - i);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	List<Position> getRightUpper(PositionInter pos, int team) {
		int row = pos.getRow();
		int col = pos.getColumn();
		List<Position> ret = new LinkedList<Position>();
		
		for (int i = 1; i <= MAXROW; i++) {
			if (row - i >= 0 && col + i <= MAXCOLUMN) {
				Position tmp = new Position(row - i, col + i);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	List<Position> getLeftLower(PositionInter pos, int team) {
		int row = pos.getRow();
		int col = pos.getColumn();
		List<Position> ret = new LinkedList<Position>();
		
		for (int i = 1; i <= MAXROW; i++) {
			if (row + i <= MAXROW && col - i >= 0) {
				Position tmp = new Position(row + i, col - i);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	List<Position> getRightLower(PositionInter pos, int team) {
		int row = pos.getRow();
		int col = pos.getColumn();
		List<Position> ret = new LinkedList<Position>();
		
		for (int i = 1; i <= MAXROW; i++) {
			if (row + i <= MAXROW && col + i <= MAXCOLUMN) {
				Position tmp = new Position(row + i, col + i);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	private List<Position> getDiagonalMovement(PositionInter pos) {
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();
		
		ret.addAll(getLeftUpper(pos,team));

		ret.addAll(getRightUpper(pos,team));

		ret.addAll(getRightLower(pos,team));
		
		ret.addAll(getLeftLower(pos,team));
		return ret;
	}

	
	
	
	List<Position> getRight(PositionInter pos,int team) {
		int col = pos.getColumn();
		int row = pos.getRow();
		List<Position> ret = new LinkedList<Position>();
	
		for (int i = 1; i <= MAXCOLUMN; i++) {
			if (col + i <= MAXCOLUMN) {
				Position tmp = new Position(row, col + i);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	List<Position> getLeft(PositionInter pos,int team) {
		int col = pos.getColumn();
		int row = pos.getRow();
		List<Position> ret = new LinkedList<Position>();
	
		for (int i = 1; i <= MAXCOLUMN; i++) {
			if (col - i >= 0) {
				Position tmp = new Position(row, col - i);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	List<Position> getUp(PositionInter pos,int team) {
		int col = pos.getColumn();
		int row = pos.getRow();
		List<Position> ret = new LinkedList<Position>();
	
		for (int i = 1; i <= MAXROW; i++) {
			if (row - i >= 0) {
				Position tmp = new Position(row - i, col);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	List<Position> getDown(PositionInter pos,int team) {
		int col = pos.getColumn();
		int row = pos.getRow();
		List<Position> ret = new LinkedList<Position>();
	
		for (int i = 1; i <= MAXROW; i++) {
			if (row + i <= MAXROW) {
				Position tmp = new Position(row + i, col);

				PieceInter hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					return ret;
				} else {
					return ret;
				}
			}
		}
		return ret;
	}
	
	private List<Position> getHorVerMovement(PositionInter pos) {
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();

		ret.addAll(getRight(pos,team));
		ret.addAll(getLeft(pos,team));
		ret.addAll(getUp(pos,team));
		ret.addAll(getDown(pos,team));

		return ret;
	}

	
	private List<Position> knightHelper(PositionInter pos, int team, int varOne, int varTwo) {
		int row = pos.getRow();
		int col = pos.getColumn();
		List<Position> ret = new LinkedList<Position>();
		
		if(row + varOne <= MAXROW) {
			if (col - varTwo >= 0) {
				Position tmp = new Position(row + varOne, col - varTwo);
				PieceInter hold = figureHolder.get(tmp);

				if (hold == null || hold.getTeam() != team) {
					ret.add(tmp);
				}
			}
			if (col + varTwo <= MAXCOLUMN) {
				Position tmp = new Position(row + varOne, col + varTwo);
				PieceInter hold = figureHolder.get(tmp);

				if (hold == null || hold.getTeam() != team) {
					ret.add(tmp);
				}
			}
		}
	
		return ret;
	}
	
	private List<Position> getKnightMovement(PositionInter pos) {
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();
		
		ret.addAll(knightHelper(pos,team,2,1));

		ret.addAll(knightHelper(pos,team,1,2));

		ret.addAll(knightHelper(pos,team,-2,1));

		ret.addAll(knightHelper(pos,team,-1,2));

		return ret;
	}
}
