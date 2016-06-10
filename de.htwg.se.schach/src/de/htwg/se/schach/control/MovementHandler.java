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
    private final int MAX_COLUMN = 7;
    private final int MAX_ROW = 7;  
    private Map<Position,Piece>figure_holder;
    
    public MovementHandler() {
        figure_holder = new HashMap<Position,Piece>();
        for (int i = 0; i <= MAX_COLUMN; i++) {
            figure_holder.put(new Position(1,i),new Pawn(1,i,1,0));
            figure_holder.put(new Position(6,i),new Pawn(6,i,-1,1));
            
            if(i == 0 || i == 7) {
                figure_holder.put(new Position(0,i),new Rook(0,i,0));
                figure_holder.put(new Position(7,i),new Rook(7,i,1));
            } else if (i == 1 || i == 6) {
                figure_holder.put(new Position(0,i),new Knight(0,i,0));
                figure_holder.put(new Position(7,i),new Knight(7,i,1));
            } else if (i == 2 || i == 5) {
                figure_holder.put(new Position(0,i),new Bishop(0,i,0));
                figure_holder.put(new Position(7,i),new Bishop(7,i,1));
            } else if (i == 3) {
                figure_holder.put(new Position(0,i),new Queen(0,i,0));
                figure_holder.put(new Position(7,i),new Queen(7,i,1));
            }
            if (i == 4) {
                figure_holder.put(new Position(0,i),new King(0,i,0));
                figure_holder.put(new Position(7,i),new King(7,i,1));
            }
        }
    }
    
    public Map<Position,Piece> getField() {
        return this.figure_holder;
    }
    
    public boolean movePiece(Position start, Position end, int team) {
        Piece hold = figure_holder.get(start);
        if(hold == null || hold.team != team) {
            return false;
        }
        List<Position> move = getMovement(start);
        if(!move.contains(end)) {
            return false;
        }
        
        if(hold instanceof Pawn) {
        	((Pawn) hold).firstMove = false;
        }
        
        figure_holder.put(end,hold);
        figure_holder.remove(start);
        return true;
    }
    
    public boolean checkPiece(Position pos, int team) {
        if(!figure_holder.containsKey(pos)) {
            return false;
        } else if (figure_holder.get(pos).team == team) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean removePiece(Position pos) {
    	if(!figure_holder.containsKey(pos)) {
    		return false;
    	}
    	figure_holder.remove(pos);
    	return true;
    }
    
    public int checkWin() {
        boolean team_1 = false;
        boolean team_0 = false;
        for (Piece pie : figure_holder.values()) {
            if(pie.getName().equals("KI")) {
                if(pie.team == 1) {
                    team_1 = true;
                } else {
                    team_0 = true;
                }
            }
        }
        if(team_1 && !team_0) {
            return 1;
        } else if (!team_1 && team_0) {
            return 0;
        } else {
            return -1;
        }
    }
    
    public List<Position> getMovement(Position pos) {
        Piece holder = figure_holder.get(pos);
        List<Position> ret = new LinkedList<Position>();
        
        if(holder == null) {
            return ret;
        }
        
        if(holder instanceof Bishop) {
            
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
        Pawn pawn = (Pawn) figure_holder.get(pos);
        List<Position> ret = new LinkedList<Position>();

        
        if (row+pawn.direction >= 0 && row+pawn.direction <= MAX_ROW) {
            Position tmp = new Position(row+pawn.direction,col);
            
            Piece hold = figure_holder.get(tmp);
            if(hold == null) {
                ret.add(tmp);
                if(pawn.firstMove) {
                    Position tmp_2 = new Position(row+2*pawn.direction,col);
                    Piece hold_2 = figure_holder.get(tmp_2);
                    if (hold_2 == null) {
                        ret.add(tmp_2);
                    }
                }
            }
            
            Position tmp_right = new Position(row+pawn.direction,col+1);
            Piece hold_right = figure_holder.get(tmp_right);
            
            if(hold_right != null && hold_right.team != pawn.team) {
                ret.add(tmp_right);
            }
            
            Position tmp_left = new Position(row+pawn.direction,col-1);
            Piece hold_left = figure_holder.get(tmp_left);
            
            if(hold_left != null && hold_left.team != pawn.team) {
                ret.add(tmp_left);
            }
        } 
        return ret;
    }
    
    private List<Position> getKingMovement(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int team = figure_holder.get(pos).team;
        List<Position> ret = new LinkedList<Position>();
        
        List<Position> ret_clean = new LinkedList<Position>();
        
        if(row+1 < 8) {
            ret.add(new Position(row+1,col));
            if(col+1 < 8) {
                ret.add(new Position(row+1,col+1));
                ret.add(new Position(row, col+1));
                
            }
            if(col-1 >= 0) {
                ret.add(new Position(row+1,col-1));
                ret.add(new Position(row, col-1));
                
            }
        }
        
        if(row-1 >= 0) {
            ret.add(new Position(row-1,col));
            if(col+1 < 8) {
                ret.add(new Position(row-1,col+1));
                if(!ret.contains(new Position(row,col+1))) {
                	ret.add(new Position(row, col+1));
                }
            }
            if(col-1 >= 0) {
                ret.add(new Position(row-1,col-1));
                if(!ret.contains(new Position(row,col-1))) {
                	ret.add(new Position(row, col-1));
                }
            }
        }
        
        for (Position pos_tmp : ret) {
            Piece hold = figure_holder.get(pos_tmp);
            
            if (!(hold != null && hold.team == team)) {
            	/*
            	if(!ret_clean.contains(pos_tmp)) {
            		ret_clean.add(pos_tmp);
            	}*/
            	ret_clean.add(pos_tmp);
            }
        }
        
        
        
        return ret_clean;
    }
    
    
    
    private List<Position> getDiagonalMovement(Position pos) {
        int row = pos.getRow();
        int col = pos.getColumn();
        int team = figure_holder.get(pos).team;
        List<Position> ret = new LinkedList<Position>();
        //Left-upper
        for (int i = 1; i <= MAX_ROW; i++) {
            if(row-i >= 0 && col-i >= 0) {
                Position tmp = new Position(row-i,col-i);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        } 

        //Right-upper
        for (int i = 1; i <= MAX_ROW; i++) {
            if(row-i >= 0 && col+i <= MAX_COLUMN) {
                Position tmp = new Position(row-i,col+i);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        }
        
        //Left-lower
        for (int i = 1; i <= MAX_ROW; i++) {
            if(row+i <= MAX_ROW && col-i >= 0) {
                Position tmp = new Position(row+i,col-i);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        }
        
        //Right-lower
        for (int i = 1; i <= MAX_ROW; i++) {
            if(row+i <= MAX_ROW && col+i <= MAX_COLUMN) {
                Position tmp = new Position(row+i,col+i);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
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
        int team = figure_holder.get(pos).team;
        List<Position> ret = new LinkedList<Position>();
        
        //Right
        for(int i = 1; i <= MAX_COLUMN; i++) {
            if(col+i <= MAX_COLUMN) {
                Position tmp = new Position(row,col+i);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        }
        
        //Left
        for(int i = 1; i <= MAX_COLUMN; i++) {
            if(col-i >= 0) {
                Position tmp = new Position(row,col-i);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        }
        
        //up
        for(int i = 1; i <= MAX_ROW; i++) {
            if(row-i >= 0) {
                Position tmp = new Position(row-i,col);
                
                Piece hold = figure_holder.get(tmp);
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        }
        
        //down
        for(int i = 1; i <= MAX_ROW; i++) {
            if(row+i <= MAX_ROW) {
                Position tmp = new Position(row+i,col);
                
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null) {
                    ret.add(tmp);
                } else if(hold.team != team) {
                    ret.add(tmp);
                    break;
                } else {
                    break;
                }
            }
        }
        
        return ret;
    }
    
    private List<Position> getKnightMovement(Position pos) { 
        int row = pos.getRow();
        int col = pos.getColumn();
        int team = figure_holder.get(pos).team;
        List<Position> ret = new LinkedList<Position>();
        
        if(row+2 <= MAX_ROW) {
            if (col-1 >= 0) {
                Position tmp = new Position(row+2,col-1);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
            if (col+1 <= MAX_COLUMN) {
                Position tmp = new Position(row+2,col+1);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
        }
        if(row+1 <= MAX_ROW) {
            if (col-2 >= 0) {
                Position tmp = new Position(row+1,col-2);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
            if (col+2 <= MAX_COLUMN) {
                Position tmp = new Position(row+1,col+2);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
        }
        if(row-2 >= 0) {
            if(col-1 >= 0) {
                Position tmp = new Position(row-2,col-1);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
            if(col+1 <= MAX_COLUMN) {
                Position tmp = new Position(row-2,col+1);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
        }
        if(row-1 >= 0) {
            if(col-2 >= 0) {
                Position tmp = new Position(row-1,col-2);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
            if(col+2 <= MAX_COLUMN) {
                Position tmp = new Position(row-1,col+2);
                Piece hold = figure_holder.get(tmp);
                
                if(hold == null || hold.team != team) {
                    ret.add(tmp);
                }
            }
        }
        
        return ret;
    }
}
