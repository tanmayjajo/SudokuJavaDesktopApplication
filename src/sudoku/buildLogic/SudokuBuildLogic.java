package sudoku.buildLogic;

import sudoku.computationlogic.GameLogic;
import sudoku.constants.GameState;
import sudoku.persistence.LocalStorageImpl;
import sudoku.persistence.LocalStorageImpl;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        } catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListner uilogic
                = new ControlLogic(storage, userInterface);

        userInterface.setListener(uilogic);
        userInterface.updateBoard(initialState);
    }
}
