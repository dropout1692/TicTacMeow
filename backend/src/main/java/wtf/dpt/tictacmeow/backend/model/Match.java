package wtf.dpt.tictacmeow.backend.model;

import lombok.Getter;
import wtf.dpt.tictacmeow.backend.model.helper.TupleList;

@Getter
public class Match {

    private TupleList players;
    private int turnIndex;
    private boolean initialized = false;

    private boolean isReadyToStart(){
        return this.players.getPlayers().size() == 2 && this.initialized;
    }

    private void initialize() {
        this.turnIndex = 0;
        this.players.randomize();
        this.initialized = true;
    }

    private Player getPlayerOnTurn() {
        return this.players.get(turnIndex);
    }

    private void nextTurn() {
        this.turnIndex = ++this.turnIndex % 2;
    }
}
