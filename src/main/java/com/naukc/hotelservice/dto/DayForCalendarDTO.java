package com.naukc.hotelservice.dto;

import lombok.Data;

@Data
public class DayForCalendarDTO {

    private int reservationId;
    private String day;
    private String type;
    private String roomName;
    private String color;

    public DayForCalendarDTO(int reservationId, String day, String type, String roomName, String color) {
        this.reservationId = reservationId;
        this.day = day;
        this.type = type;
        this.roomName = roomName;
        this.color = color;
    }
}
