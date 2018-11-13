package com.twentythree.space.controller;

import com.twentythree.space.entity.Player;
import com.twentythree.space.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/player")
    List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping("/player")
    Player createPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }
}
