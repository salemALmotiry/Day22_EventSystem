package com.example.day22_eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {

    @NotNull(message = "Enter event id")
    @Positive(message = "Event id muse be positive number")
    @Min(value = 2 , message = "Event id muse be longer than 2")
    private int ID;

    @NotEmpty(message = "Enter event description")
    @Size(min = 15,message = "Event description length must be more than 15 ")
    private String description;


    @Positive(message = "Capacity must be positive number")
    @Min(value = 25,message = "Capacity must be more than 25")
    private int capacity;

    @FutureOrPresent(message = "Event start date must be future or present date ")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd 'T' hh:mm")
    private LocalDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd 'T' hh:mm")
    @Future(message = "Event end date must be future date ")
    private LocalDateTime endDate;

}