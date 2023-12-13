package com.example.hotelservice.service;

import com.example.hotelservice.models.Room;

import java.util.Optional;

public interface RoomService {


    void add(Room room);

    boolean delete(int id);

    Optional<Room> findById(int id);
}
