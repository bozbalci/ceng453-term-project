package com.twentythree.space.controller;

import com.twentythree.space.entity.Player;
import com.twentythree.space.exception.PlayerNotFoundException;
import com.twentythree.space.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/player")
    Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @GetMapping("/player")
    List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping("/player/{id}")
    Player getPlayer(@PathVariable long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
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
}
