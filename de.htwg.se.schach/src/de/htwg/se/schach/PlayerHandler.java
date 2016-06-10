/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.se.schach;


import java.util.Scanner;
import de.htwg.se.schach.view.*;
import de.htwg.se.schach.control.Position;
/**
 *
 * @author rob
 */
public class PlayerHandler {
    private ChessView view;
    
    
    public PlayerHandler() {
        start_game();
        game_handler();
    }
    
    
    public void start_game() {
        this.view = new ChessView();
        view.printField();
    }
    
    public void handleShow(String inp, int team) {
        String[] parts = inp.split(" ");
        if (parts.length != 2) {
            System.out.println(">>> Invalid command!");
        }

        // - 65 to map A to 0, B to 1, ...
        int col_start = ((int) parts[1].charAt(0)) - 65;
        // - 49 to map '1' to 0, '2' to 1, ...
        int row_start = (int) parts[1].charAt(1) - 49;
        
        if(!checkValue(col_start)) {
            System.out.println(">>> The column must be between A and H!");
            return;
        } else if (!checkValue(row_start)) {
            System.out.println(">>> The row must be between 0 and 7!");
            return;
        }
        Position start = new Position(row_start, col_start);
        
        view.viewMovement(start);
        
        
    }
    
    public boolean handleInput(String inp,int team) {
        if (inp.equals("quit")) {
            return true;
        } else if (inp.startsWith("show")) {
            handleShow(inp,team);
            return false;
        }
        
        String[] parts = inp.split("-");
        if (parts.length != 2) {
            System.out.println(">>> Invalid command!");
            return false;
        }
        if(parts[0].length() != 2 || parts[1].length() != 2) {
            System.out.println(">>> Invalid command!");
            return false;
        }
        // - 65 to map A to 0, B to 1, ...
        int col_start = ((int) parts[0].charAt(0)) - 65;
        // - 49 to map '1' to 0, '2' to 1, ...
        int row_start = (int) parts[0].charAt(1) - 49;
        
        if(!checkValue(col_start)) {
            System.out.println(">>> The column must be between A and H!");
            return false;
        } else if (!checkValue(row_start)) {
            System.out.println(">>> The row must be between 0 and 7!");
            return false;
        }
        Position start = new Position(row_start, col_start);
        if(!view.checkPiece(start, team)) {
            System.out.println(">>> None of your Pieces are on this position!");
            return false;
        }
        
        // - 65 to map A to 0, B to 1, ...
        int col_end = ((int) parts[1].charAt(0)) - 65;
        // - 49 to map '1' to 0, '2' to 1, ...
        int row_end = (int) parts[1].charAt(1) - 49;
        
        if(!checkValue(col_end)) {
            System.out.println(">>> The column must be between A and H!");
            return false;
        } else if (!checkValue(row_end)) {
            System.out.println(">>> The row must be between 0 and 7!");
            return false;
        }
        
        Position end = new Position(row_end, col_end);
        if(view.movePiece(start, end, team)) {
            return true;
        } else {
            System.out.println(">>> Unknown Error : Could not make the move!");
            return false;
        }
    }
    
    public boolean checkValue(int val) {
        return val <= 7 && val >= 0;
    }
    
    
    
    public void game_handler() {
        int turn = 1;
        Scanner scan = new Scanner(System.in);
        String holder;
        boolean quit_flag = false;
        System.out.println("HINT: Write the movement like this: A2-A3");
        System.out.println("HINT: To see aviable moves: show D7");
        System.out.println(">>> Game started:");
        
        while (!quit_flag) {
            System.out.printf(">>>[Player%d]: ",turn);
            holder = scan.nextLine();
            if(holder.equals("quit")) {
                quit_flag = true;
            }
            
            if(handleInput(holder,turn)) {
                view.printField();
                turn = (turn+1)%2;
            }
        }
        System.out.println(">>> Closing now!");
        scan.close();
    }
}
