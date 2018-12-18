package com.twentythree.space.controller;

import com.twentythree.space.entity.Player;
import com.twentythree.space.exception.ForbiddenException;
import com.twentythree.space.exception.PlayerNotFoundException;
import com.twentythree.space.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/player")
    long createPlayer(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        String encodedPassword = passwordEncoder.encode(password);

        final Player created = playerRepository.save(new Player(username, encodedPassword));
        return created.getId();
    }

    @GetMapping("/player")
    List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/player/whoami")
    long whoami(Authentication auth) {
        if (auth == null) {
            throw new ForbiddenException();
        }

        final Player player = (Player) auth.getPrincipal();

        return player.getId();
    }

    @GetMapping("/player/{id}")
    Player getPlayer(@PathVariable long id, Authentication auth) {
        if (auth == null) {
            throw new ForbiddenException();
        }

        final Player player = (Player) auth.getPrincipal();

        if (player.getId() == id) {
            return player;
        } else if ("ADMIN".equals(player.getRole())) {
            return playerRepository.findById(id)
                    .orElseThrow(() -> new PlayerNotFoundException(id));
        } else {
            throw new ForbiddenException();
        }
    }

    @PutMapping("/player")
    Player updatePlayer(@RequestBody Player newPlayer) {
        long id = newPlayer.getId();

        playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        return playerRepository.save(newPlayer);
    }

    @DeleteMapping("/player/{id}")
    void deletePlayer(@PathVariable long id) {
        playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        playerRepository.deleteById(id);
    }

    public Player findById(long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
    }
}
