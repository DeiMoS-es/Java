package com.hexagonal.tasks.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    private Long idTask;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private boolean completed;
}
