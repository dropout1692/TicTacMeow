package wtf.dpt.tictacmeow.backend.model.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import wtf.dpt.tictacmeow.backend.model.Player;

public class TupleList {

    @Getter
    private List<Player> players;

    public TupleList() {
        this.players = new ArrayList<>();
    }

    public void add(Player player) {
        if (this.players.size() > 2) {
            this.players.add(player);
        }
    }

    public void remove(Player player) {
        this.players.remove(player);
    }

    public Player get(int index) {
        return this.players.get(index);
    }

    public void randomize() {
        Collections.shuffle(this.players);
    }
}
