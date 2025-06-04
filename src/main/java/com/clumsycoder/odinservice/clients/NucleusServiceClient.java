package com.clumsycoder.odinservice.clients;

import com.clumsycoder.odinservice.dto.Player;
import com.clumsycoder.odinservice.dto.internal.CreatePlayerRequest;
import com.clumsycoder.odinservice.dto.request.UpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "nucleus-service", url = "http://localhost:9000")
public interface NucleusServiceClient {

    @GetMapping("/api/player/{id}")
    Player getPlayerById(@PathVariable("id") String id);

    @PostMapping("/api/player")
    Player createPlayer(@RequestBody CreatePlayerRequest request);

    @PatchMapping("/api/player/{id}")
    Player updatePlayer(@PathVariable("id") String id, @RequestBody UpdateRequest player);
}