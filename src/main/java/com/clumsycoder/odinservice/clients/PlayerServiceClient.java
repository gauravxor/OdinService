package com.clumsycoder.odinservice.clients;

import com.clumsycoder.odinservice.dto.common.Player;
import com.clumsycoder.odinservice.dto.internal.PlayerAuthResponse;
import com.clumsycoder.odinservice.dto.request.PlayerSignupRequest;
import com.clumsycoder.odinservice.dto.request.PlayerUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:9000")
public interface PlayerServiceClient {

    @GetMapping("/api/player/{id}")
    Player getPlayerById(@PathVariable("id") String id);

    @GetMapping("/api/player/{id}/auth")
    PlayerAuthResponse getPlayerAuthDataByEmail(@PathVariable("id") String id);

    @PostMapping("/api/player")
    Player createPlayer(@RequestBody PlayerSignupRequest user);

    @PatchMapping("/api/player/{id}")
    Player updatePlayer(@PathVariable("id") String id, @RequestBody PlayerUpdateRequest player);
}