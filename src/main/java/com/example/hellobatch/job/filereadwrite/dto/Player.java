package com.example.hellobatch.job.filereadwrite.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private String id;
    private String lastName;
    private String firstName;
    private String position;
    private String birthYear;
    private String debutYear;
}
