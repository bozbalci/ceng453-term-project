package com.twentythree.space.controller;

import com.twentythree.space.constants.SpaceAppConstants;
import com.twentythree.space.entity.Leaderboard;
import com.twentythree.space.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/weekly")
    Leaderboard getSevenDaysLeaderboard() {
        List<Object[]> list = matchRepository.getSevenDaysLeaderboard(SpaceAppConstants.LEADERBOARD_WEEKLY_LIMIT);

        return Leaderboard.fromNativeQueryList(list);
    }

    @GetMapping("/all")
    Leaderboard getAllTimeLeaderboard() {
        List<Object[]> list = matchRepository.getAllTimeLeaderboard(SpaceAppConstants.LEADERBOARD_ALL_TIME_LIMIT);

        return Leaderboard.fromNativeQueryList(list);
    }
}
