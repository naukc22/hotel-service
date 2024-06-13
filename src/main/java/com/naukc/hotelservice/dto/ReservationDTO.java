package com.naukc.hotelservice.dto;


import lombok.Data;



@Data
public class ReservationDTO {

    private String dateIn;

    private String dateExit;

    private int room;

    private String name;

}
