package ai;

import ai.node_heuristic.NodeHeuristic;
import game.Game;
import game.PlayerType;

import java.util.ArrayList;

public class MinMaxAI implements Heuristic {

    private static Game currentGame;
    private static PlayerType currentPlayerType;
    private static PlayerType oppositePlayerType;
    private static Move localBestMove;
    private static int globalTreeDepth;
    private static Move globalBestMove;
    private static NodeHeuristic nodeHeuristic;

    public static Move getNextMove(int treeDepth, Game currentGame, PlayerType currentPlayerType,
                                   boolean shouldUseAlphaBeta, NodeHeuristic nodeHeuristic){
        MinMaxAI.nodeHeuristic = nodeHeuristic;
        MinMaxAI.currentGame = currentGame;
        MinMaxAI.currentPlayerType = currentPlayerType;
        MinMaxAI.oppositePlayerType = currentPlayerType == PlayerType.GREEN ? PlayerType.RED : PlayerType.GREEN;
        MinMaxAI.globalTreeDepth = treeDepth;
        Move bestMove = shouldUseAlphaBeta ? getAlphaBetaMove(treeDepth) : getMinMaxMove(treeDepth);
        resetMinMax();

        return bestMove;
    }

    private static Move getMinMaxMove(int treeDepth){
        ArrayList<Move> availableMoves = nodeHeuristic.getAvailableMoves(currentGame.getBoard());
        minimax(availableMoves, treeDepth, true);
        return globalBestMove;
    }

    private static Move getAlphaBetaMove(int treeDepth){
        ArrayList<Move> availableMoves = nodeHeuristic.getAvailableMoves(currentGame.getBoard());
        alphaBetaPruning(availableMoves, treeDepth, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
        return globalBestMove;
    }

    private static int minimax(ArrayList<Move> availableMoves, int depth, boolean isMaximizer){

        if (depth == 0 || availableMoves.size() == 0){
            if(currentPlayerType == PlayerType.GREEN){
                return currentGame.getGreenPlayerScore() - currentGame.getRedPlayerScore();
            }
            else {
                return currentGame.getRedPlayerScore() - currentGame.getGreenPlayerScore();
            }
        }

        if(isMaximizer){
            int bestValue = Integer.MIN_VALUE;

            for(int i = 0; i < availableMoves.size(); i++){
                Move currentMove = availableMoves.get(i);

                int initialGreenPlayerScore = currentGame.getGreenPlayerScore();
                int initialRedPlayerScore = currentGame.getRedPlayerScore();

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(true);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(currentPlayerType);
                currentGame.calculatePoints(currentPlayerType, currentMove.getX(), currentMove.getY());
                availableMoves.remove(i);

                int value = minimax(availableMoves, depth - 1, false);

                //Get back removed move
                availableMoves.add(i, currentMove);

                currentGame.setGreenPlayerScore(initialGreenPlayerScore);
                currentGame.setRedPlayerScore(initialRedPlayerScore);

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(false);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(null);


                if(value > bestValue){
                    bestValue = value;
                    localBestMove = currentMove;

                    //Save current state
                    if(MinMaxAI.globalTreeDepth == depth){
                        globalBestMove = currentMove;
                    }

                }
            }

            return bestValue;
        }

        else { //Minimizer
            int bestValue = Integer.MAX_VALUE;

            for(int i = 0; i < availableMoves.size(); i++){
                Move currentMove = availableMoves.get(i);

                int initialGreenPlayerScore = currentGame.getGreenPlayerScore();
                int initialRedPlayerScore = currentGame.getRedPlayerScore();

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(true);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(oppositePlayerType);
                currentGame.calculatePoints(oppositePlayerType, currentMove.getX(), currentMove.getY());

                availableMoves.remove(i);
                int value = minimax(availableMoves, depth - 1, true);

                //Get back removed move
                availableMoves.add(i, currentMove);

                currentGame.setGreenPlayerScore(initialGreenPlayerScore);
                currentGame.setRedPlayerScore(initialRedPlayerScore);

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(false);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(null);

                if(value < bestValue){
                    bestValue = value;
                    localBestMove = currentMove;
                }
            }

            return bestValue;
        }
    }

    private static int alphaBetaPruning(ArrayList<Move> availableMoves, int depth, int alpha, int beta, boolean isMaximizer){

        if (depth == 0 || availableMoves.size() == 0){
            if(currentPlayerType == PlayerType.GREEN){
                return currentGame.getGreenPlayerScore() - currentGame.getRedPlayerScore();
            }
            else {
                return currentGame.getRedPlayerScore() - currentGame.getGreenPlayerScore();
            }
        }

        if(isMaximizer){
            int bestValue = Integer.MIN_VALUE;

            for(int i = 0; i < availableMoves.size(); i++){
                Move currentMove = availableMoves.get(i);

                int initialGreenPlayerScore = currentGame.getGreenPlayerScore();
                int initialRedPlayerScore = currentGame.getRedPlayerScore();

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(true);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(currentPlayerType);
                currentGame.calculatePoints(currentPlayerType, currentMove.getX(), currentMove.getY());
                availableMoves.remove(i);

                int value = alphaBetaPruning(availableMoves, depth - 1, alpha, beta, false);

                //Get back removed move
                availableMoves.add(i, currentMove);

                currentGame.setGreenPlayerScore(initialGreenPlayerScore);
                currentGame.setRedPlayerScore(initialRedPlayerScore);

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(false);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(null);


                if(value > bestValue){
                    bestValue = value;
                    localBestMove = currentMove;

                    //Save best state
                    if(MinMaxAI.globalTreeDepth == depth){
                        globalBestMove = currentMove;
                    }
                }

                // Beta cutting
                alpha = Math.max(alpha, bestValue);
                if(beta <= alpha){
                    break;
                }
            }

            return bestValue;
        }

        else { //Minimizer
            int bestValue = Integer.MAX_VALUE;

            for(int i = 0; i < availableMoves.size(); i++){
                Move currentMove = availableMoves.get(i);

                int initialGreenPlayerScore = currentGame.getGreenPlayerScore();
                int initialRedPlayerScore = currentGame.getRedPlayerScore();

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(true);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(oppositePlayerType);
                currentGame.calculatePoints(oppositePlayerType, currentMove.getX(), currentMove.getY());

                availableMoves.remove(i);
                int value = alphaBetaPruning(availableMoves, depth - 1, alpha, beta, true);

                //Get back removed move
                availableMoves.add(i, currentMove);

                currentGame.setGreenPlayerScore(initialGreenPlayerScore);
                currentGame.setRedPlayerScore(initialRedPlayerScore);

                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setFilled(false);
                currentGame.getBoard()[currentMove.getY()][currentMove.getX()].setPlayerType(null);

                if(value < bestValue){
                    bestValue = value;
                    localBestMove = currentMove;
                }

                // Alpha cutting
                beta = Math.min(beta, bestValue);
                if (beta <= alpha){
                   break;
                }
            }

            return bestValue;
        }
    }

    public static void resetMinMax(){
        MinMaxAI.currentGame = null;
        MinMaxAI.currentPlayerType = null;
        MinMaxAI.oppositePlayerType = null;
        MinMaxAI.localBestMove = null;
        MinMaxAI.globalTreeDepth = 0;
        MinMaxAI.globalBestMove = null;
    }

}
