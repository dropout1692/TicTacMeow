package wtf.dpt.tictacmeow.backend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtf.dpt.tictacmeow.backend.dtos.AuthDTO;
import wtf.dpt.tictacmeow.backend.model.Player;
import wtf.dpt.tictacmeow.backend.services.AuthService;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private AuthService authService;

    @GetMapping("/get-players")
    public List<String> getPlayerNames(){
        return authService.getActivePlayerNames();
    }

    @PostMapping("/register")
    public Player register(
        @RequestBody AuthDTO authDTO
    ){
        return authService.authorize(authDTO);
    }

    @PostMapping("/pre-register")
    public boolean checkNameAvailability(String username){
        return authService.isActivePlayer(username);
    }
}
