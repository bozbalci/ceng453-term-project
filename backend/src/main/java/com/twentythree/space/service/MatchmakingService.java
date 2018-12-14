package com.twentythree.space.service;

import com.twentythree.space.entity.LiveMatchManagerSingleton;
import com.twentythree.space.entity.Player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class MatchmakingService {
    private static MatchmakingService instance = new MatchmakingService();

    private MatchmakingService() {
        queue = new LinkedList<>();
        requestMonitor = new HashMap<>();
    }

    public static MatchmakingService getInstance() {
        return instance;
    }

    private Queue<Player> queue;
    private HashMap<Long, Object> requestMonitor;

    public Optional<Long> enlist(Player player) {
        queue.add(player);

        requestMonitor.put(player.getId(), new Object());

        Long matchId;
        long queuedPlayerCount = queue.size();

        if (queuedPlayerCount >= 2) {
            Player playerOne = queue.remove();
            Player playerTwo = queue.remove();

            matchId = LiveMatchManagerSingleton.getInstance()
                    .createLiveMatch(playerOne.getId(), playerTwo.getId());

            notifyPlayerById(playerOne.getId());
            notifyPlayerById(playerTwo.getId());

            removeFromMonitor(playerOne.getId());
            removeFromMonitor(playerTwo.getId());

            return Optional.of(matchId);
        }

        return Optional.empty();
    }

    public void removePlayerById(long targetId) {
        queue.removeIf(player -> player.getId() == targetId);
    }

    private void notifyPlayerById(long playerId) {
        Object lock = requestMonitor.get(playerId);

        if (lock == null) {
            return;
        }

        synchronized (lock) {
            lock.notify();
        }

        removeFromMonitor(playerId);
    }

    public Boolean waitPlayerById(long playerId) {
        Object lock = requestMonitor.get(playerId);

        if (lock == null) {
            return false;
        }

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                return false;
            }
        }

        return true;
    }

    private void removeFromMonitor(long playerId) {
        requestMonitor.remove(playerId);
    }
}
