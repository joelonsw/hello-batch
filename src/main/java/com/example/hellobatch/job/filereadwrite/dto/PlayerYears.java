package com.example.hellobatch.job.filereadwrite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Year;

@AllArgsConstructor
@Data
public class PlayerYears {
    private String id;
    private String lastName;
    private String firstName;
    private String position;
    private String birthYear;
    private String debutYear;
    private Integer yearsExperience;

    public static PlayerYears from(Player player) {
        return new PlayerYears(
                player.getId(),
                player.getLastName(),
                player.getFirstName(),
                player.getPosition(),
                player.getBirthYear(),
                player.getDebutYear(),
                Year.now().getValue() - Integer.parseInt(player.getDebutYear())
        );
    }
}
