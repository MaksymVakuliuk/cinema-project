package com.dev.cinema.model.dto.cinemahall;

import javax.validation.constraints.NotNull;

public class CinemaHallRequestDto {
    @NotNull
    private int capacity;
    @NotNull(message = "Please provide a description of cinema hall")
    private String description;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
