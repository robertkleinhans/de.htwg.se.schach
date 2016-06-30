/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach.control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import de.htwg.se.schach.model.*;

/**
 *
 * @author rob
 */
public class MovementHandler {
	private static final  int maxCOLUMN = 7;
	private static final  int maxROW = 7;
	private Map<Position, Piece> figureHolder;

	public MovementHandler() {
		figureHolder = new HashMap<Position, Piece>();
		for (int i = 0; i <= maxCOLUMN; i++) {
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

	public Map<Position, Piece> getField() {
		return this.figureHolder;
	}

	public boolean movePiece(Position start, Position end, int team) {
		Piece hold = figureHolder.get(start);
		if (hold == null || hold.getTeam() != team) {
			return false;
		}
		List<Position> move = getMovement(start);
		if (!move.contains(end)) {
			return false;
		}

		if (hold instanceof Pawn) {
			((Pawn) hold).setFirstMove(false);
		}

		figureHolder.put(end, hold);
		figureHolder.remove(start);
		return true;
	}

	public boolean checkPiece(Position pos, int team) {
		if (!figureHolder.containsKey(pos)) {
			return false;
		} else {
			return figureHolder.get(pos).getTeam() == team;
		}
	}

	public boolean removePiece(Position pos) {
		if (!figureHolder.containsKey(pos)) {
			return false;
		}
		figureHolder.remove(pos);
		return true;
	}

	public int checkWin() {
		boolean team1 = false;
		boolean team0 = false;
		for (Piece pie : figureHolder.values()) {
			if (("KI").equals(pie.getName()) && pie.getTeam() == 1) {
				team1 = true;
			} else if (("KI").equals(pie.getName()) && pie.getTeam() == 0) {
				team0 = true;
			}
		}
		if (team1 && !team0) {
			return 1;
		} else if (!team1 && team0) {
			return 0;
		} else {
			return -1;
		}
	}

	public List<Position> getMovement(Position pos) {
		Piece holder = figureHolder.get(pos);
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

	private List<Position> getPawnMovement(Position pos) {
		int row = pos.getRow();
		int col = pos.getColumn();
		Pawn pawn = (Pawn) figureHolder.get(pos);
		List<Position> ret = new LinkedList<Position>();

		if (row + pawn.getDirection() >= 0 && row + pawn.getDirection() <= maxROW) {
			Position tmp = new Position(row + pawn.getDirection(), col);

			Piece hold = figureHolder.get(tmp);
			if (hold == null) {
				ret.add(tmp);

				Position tmp2 = new Position(row + 2 * pawn.getDirection(), col);
				Piece hold2 = figureHolder.get(tmp2);
				if (pawn.isFirstMove() && hold2 == null) {
					ret.add(tmp2);
				}
			}

			Position tmpRight = new Position(row + pawn.getDirection(), col + 1);
			Piece holdRight = figureHolder.get(tmpRight);

			if (holdRight != null && holdRight.getTeam() != pawn.getTeam()) {
				ret.add(tmpRight);
			}

			Position tmpLeft = new Position(row + pawn.getDirection(), col - 1);
			Piece holdLeft = figureHolder.get(tmpLeft);

			if (holdLeft != null && holdLeft.getTeam() != pawn.getTeam()) {
				ret.add(tmpLeft);
			}
		}
		return ret;
	}

	private List<Position> getKingMovement(Position pos) {
		int row = pos.getRow();
		int col = pos.getColumn();
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();

		List<Position> retClean = new LinkedList<Position>();

		if (row + 1 < 8) {
			ret.add(new Position(row + 1, col));
			if (col + 1 < 8) {
				ret.add(new Position(row + 1, col + 1));
				ret.add(new Position(row, col + 1));

			}
			if (col - 1 >= 0) {
				ret.add(new Position(row + 1, col - 1));
				ret.add(new Position(row, col - 1));

			}
		}

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
			Piece hold = figureHolder.get(posTmp);

			if (!(hold != null && hold.getTeam() == team)) {
				retClean.add(posTmp);
			}
		}
		return retClean;
	}

	private List<Position> getDiagonalMovement(Position pos) {
		int row = pos.getRow();
		int col = pos.getColumn();
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();
		// Left-upper
		for (int i = 1; i <= maxROW; i++) {
			if (row - i >= 0 && col - i >= 0) {
				Position tmp = new Position(row - i, col - i);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		// Right-upper
		for (int i = 1; i <= maxROW; i++) {
			if (row - i >= 0 && col + i <= maxCOLUMN) {
				Position tmp = new Position(row - i, col + i);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		// Left-lower
		for (int i = 1; i <= maxROW; i++) {
			if (row + i <= maxROW && col - i >= 0) {
				Position tmp = new Position(row + i, col - i);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		// Right-lower
		for (int i = 1; i <= maxROW; i++) {
			if (row + i <= maxROW && col + i <= maxCOLUMN) {
				Position tmp = new Position(row + i, col + i);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}
		return ret;
	}

	private List<Position> getHorVerMovement(Position pos) {
		int row = pos.getRow();
		int col = pos.getColumn();
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();

		// Right
		for (int i = 1; i <= maxCOLUMN; i++) {
			if (col + i <= maxCOLUMN) {
				Position tmp = new Position(row, col + i);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		// Left
		for (int i = 1; i <= maxCOLUMN; i++) {
			if (col - i >= 0) {
				Position tmp = new Position(row, col - i);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		// up
		for (int i = 1; i <= maxROW; i++) {
			if (row - i >= 0) {
				Position tmp = new Position(row - i, col);

				Piece hold = figureHolder.get(tmp);
				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		// down
		for (int i = 1; i <= maxROW; i++) {
			if (row + i <= maxROW) {
				Position tmp = new Position(row + i, col);

				Piece hold = figureHolder.get(tmp);

				if (hold == null) {
					ret.add(tmp);
				} else if (hold.getTeam() != team) {
					ret.add(tmp);
					break;
				} else {
					break;
				}
			}
		}

		return ret;
	}

	
	private List<Position> knightHelper(Position pos, int team, int varOne, int varTwo) {
		int row = pos.getRow();
		int col = pos.getColumn();
		List<Position> ret = new LinkedList<Position>();
		
		if(row + varOne <= maxROW) {
			if (col - varTwo >= 0) {
				Position tmp = new Position(row + varOne, col - varTwo);
				Piece hold = figureHolder.get(tmp);

				if (hold == null || hold.getTeam() != team) {
					ret.add(tmp);
				}
			}
			if (col + varTwo <= maxCOLUMN) {
				Position tmp = new Position(row + varOne, col + varTwo);
				Piece hold = figureHolder.get(tmp);

				if (hold == null || hold.getTeam() != team) {
					ret.add(tmp);
				}
			}
		}
	
		return ret;
	}
	
	private List<Position> getKnightMovement(Position pos) {
		int team = figureHolder.get(pos).getTeam();
		List<Position> ret = new LinkedList<Position>();
		
		ret.addAll(knightHelper(pos,team,2,1));

		ret.addAll(knightHelper(pos,team,1,2));

		ret.addAll(knightHelper(pos,team,-2,1));

		ret.addAll(knightHelper(pos,team,-1,2));

		return ret;
	}
}
