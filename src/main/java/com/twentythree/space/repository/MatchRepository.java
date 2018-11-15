package com.twentythree.space.repository;

import com.twentythree.space.entity.Match;
import com.twentythree.space.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByMatchIdAndPlayer(long matchId, Player player);
}
