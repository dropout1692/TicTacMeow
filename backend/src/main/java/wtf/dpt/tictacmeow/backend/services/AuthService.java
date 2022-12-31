package wtf.dpt.tictacmeow.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Service;
import wtf.dpt.tictacmeow.backend.crypto.TokenGenerator;
import wtf.dpt.tictacmeow.backend.dtos.AuthDTO;
import wtf.dpt.tictacmeow.backend.model.Player;

@Service
public class AuthService {

    @Getter
    private List<Player> activePlayers;

    public AuthService() {
        this.activePlayers = new ArrayList<>();
    }

    public Player authorize(AuthDTO authDTO) {

        String authToken = TokenGenerator.generate(authDTO);
        Player tempPlayer = new Player(authDTO.getUsername(), authToken);

        if (isActivePlayer(tempPlayer.getUsername())) {

            Player existingPlayer = getPlayer(tempPlayer.getUsername());
            if (existingPlayer.getToken().equals(tempPlayer.getToken())) {
                return existingPlayer;
            } else {
                return null; //unauthorized - bad pwd
            }

        } else {

            this.activePlayers.add(tempPlayer);
            return tempPlayer;
        }
    }

    public List<String> getActivePlayerNames(){

        return this.activePlayers.stream()
            .map(Player::getUsername)
            .collect(Collectors.toList());
    }

    public boolean isActivePlayer(String username) {

        for (Player activePlayer : this.activePlayers) {
            if (activePlayer.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    private Player getPlayer(String username) {

        for (Player player : this.activePlayers) {
            if (player.getUsername().toLowerCase().equals(username)) {
                return player;
            }
        }

        return null;
    }
}
