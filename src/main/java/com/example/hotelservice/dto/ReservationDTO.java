package com.example.hotelservice.dto;

import com.example.hotelservice.models.Room;
import lombok.Data;

@Data
public class ReservationDTO {

    private String check_in;
    private String check_out;
    private int room;

}
