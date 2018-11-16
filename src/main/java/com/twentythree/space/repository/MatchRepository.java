package com.twentythree.space.repository;

import com.twentythree.space.entity.Match;
import com.twentythree.space.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByMatchIdAndPlayer(long matchId, Player player);

    @Query(value = "SELECT p.username, SUM(gm.score) as total_score\n" +
            "FROM game_match gm\n" +
            "INNER JOIN player p\n" +
            "ON gm.player_id = p.id\n" +
            "GROUP BY p.id\n" +
            "ORDER BY total_score DESC\n" +
            "LIMIT :limit", nativeQuery = true)
    List<Object[]> getAllTimeLeaderboard(@Param("limit") long limit);

    @Query(value = "SELECT p.username, SUM(gm.score) as total_score\n" +
            "FROM game_match gm\n" +
            "INNER JOIN player p\n" +
            "ON gm.player_id = p.id\n" +
            "WHERE gm.created_at BETWEEN DATE_SUB(NOW(), INTERVAL 7 DAY) AND NOW()\n" +
            "GROUP BY p.id\n" +
            "ORDER BY total_score DESC\n" +
            "LIMIT :limit", nativeQuery = true)
    List<Object[]> getSevenDaysLeaderboard(@Param("limit") long limit);
}
