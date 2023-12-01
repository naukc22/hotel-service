package com.example.hotelservice.service;

import com.example.hotelservice.models.Room;

public interface RoomService {


    void add(Room room);

    boolean delete(int id);
}
