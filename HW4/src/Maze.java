

import java.util.*;

import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;

import java.lang.*;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 * @author Zimu Jiao.()
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if(x>=0 && x<maze.getNCols() && y>=0 && y<maze.getNRows() && maze.getColor(x,y)==NON_BACKGROUND) {  // When x and y are both in range and it can move(in red):
            if(x==(maze.getNCols()-1) && y==(maze.getNRows()-1)) {		// If reach the point (Col-1,Row-1)
                maze.recolor(x,y,PATH);
                return true;
            }else {
                maze.recolor(x,y,PATH);		// Paint the current cell. Keep find:
                if(findMazePath(x+1,y) || findMazePath(x,y+1) || findMazePath(x-1,y) || findMazePath(x,y-1) ) { // Recursively Try Finding way:
                    return true;
                }else {		// Else, when not find way: turn the current cell into black, and report fail:
                    maze.recolor(x,y,TEMPORARY);
                    return false;
                }
            }
        }
        return false;
    }


    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y){
        ArrayList<ArrayList<PairInt>> result= new ArrayList<>();
        Stack<PairInt> trace= new Stack<>();
        findMazePathStackBased(0,0,result,trace);
        return result;
    }

    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        if(x>=0 && y>=0 && x<=maze.getNCols()-1 && y<=maze.getNRows()-1 && maze.getColor(x,y).equals(NON_BACKGROUND)){
            // When x/y IS in the board and the current cell IS NON_BACKGROUND
            trace.push(new PairInt(x,y));	// Add point to result;
            if(x==(maze.getNCols()-1) && y==(maze.getNRows()-1)){
                ArrayList<PairInt> current= new ArrayList<>(trace);
                result.add(current);
                trace.pop();
                maze.recolor(x,y,NON_BACKGROUND);	// Visited cell remove from trace and reprinted.
            }else {
                maze.recolor(x,y,PATH);
                //	Recursive:
                findMazePathStackBased(x+1,y,result,trace);
                findMazePathStackBased(x-1,y,result,trace);
                findMazePathStackBased(x,y+1,result,trace);
                findMazePathStackBased(x,y-1,result,trace);

                trace.pop();
                maze.recolor(x,y,NON_BACKGROUND);	//Visited cell remove from trace and reprinted.
            }
        }
    }

    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x,int y){

        maze.recolor(PATH,NON_BACKGROUND);

        ArrayList<ArrayList<PairInt>> result=findAllMazePaths(x,y);

        if(result.size()==0){	// When there IS NO result(solution):
            return new ArrayList<PairInt> ();	// Return Zero answer
        }else {					// Else return the min value. Get it by loop:
            ArrayList<PairInt> min= result.get(0);
            int minLength=min.size();
            for(int i=0;i<result.size();i++){
                if(minLength>=result.get(i).size()){
                    min=result.get(i);
                    minLength=min.size();
                }
            }
            return min;
        }
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
