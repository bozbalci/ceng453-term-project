package com.twentythree.space.controller;

import com.twentythree.space.constants.SpaceAppConstants;
import com.twentythree.space.entity.Player;
import com.twentythree.space.exception.ForbiddenException;
import com.twentythree.space.service.MatchmakingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/queue")
public class QueueController {
    @PostMapping("/enlist")
    DeferredResult<ResponseEntity<?>> enlist(Authentication auth) {
        if (auth == null) {
            throw new ForbiddenException();
        }

        DeferredResult<ResponseEntity<?>> output =
                new DeferredResult<>(SpaceAppConstants.MATCHMAKING_TIMEOUT);

        final Player player = (Player) auth.getPrincipal();
        Optional<Long> enlistResult = MatchmakingService.getInstance().enlist(player);

        if (!enlistResult.isPresent()) {
            ForkJoinPool.commonPool().submit(() -> {
                Boolean found = MatchmakingService.getInstance()
                        .waitPlayerById(player.getId());

                if (!found) {
                    MatchmakingService.getInstance().removePlayerById(player.getId());

                    output.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT));
                } else {
                    output.setResult(ResponseEntity.ok(null));
                }
            });
        } else {
            output.setResult(ResponseEntity.ok(null));
        }

        output.onTimeout(() -> {
            // Remove player on matchmaking timeout
            MatchmakingService.getInstance().removePlayerById(player.getId());

            output.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(null));
        });

        output.onError((Throwable t) -> {
            // Remove player on matchmaking exceptions and errors
            MatchmakingService.getInstance().removePlayerById(player.getId());

            output.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null));
        });

        return output;
    }
}
