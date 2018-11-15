package com.twentythree.space.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "game_match",
        uniqueConstraints = @UniqueConstraint(columnNames =
                {"match_id", "player_id"}))
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "match_id")
    long matchId;

    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    @Enumerated
    @Column(name = "match_type")
    MatchType matchType;

    @Column(name = "score")
    long score;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    Date createdAt;

    protected Match() {}

    public Match(long matchId, Player player, MatchType matchType, long score, Date createdAt) {
        this.matchId = matchId;
        this.player = player;
        this.matchType = matchType;
        this.score = score;
        this.createdAt = createdAt;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", player=" + player +
                ", matchType=" + matchType +
                ", score=" + score +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchId == match.matchId &&
                score == match.score &&
                Objects.equals(player, match.player) &&
                matchType == match.matchType &&
                Objects.equals(createdAt, match.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, player, matchType, score, createdAt);
    }
}
