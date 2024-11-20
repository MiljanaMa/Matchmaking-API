package com.example.matchmaking.dto;

import com.example.matchmaking.mapper.DtoEntity;
import jakarta.validation.constraints.NotEmpty;

public class PlayerRequestDto implements DtoEntity {
    @NotEmpty(message = "Nickname is required")
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
