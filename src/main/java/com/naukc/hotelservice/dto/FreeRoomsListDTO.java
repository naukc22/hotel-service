package com.naukc.hotelservice.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class FreeRoomsListDTO {

    private HashMap<Integer,String> list = new HashMap<>();

}
