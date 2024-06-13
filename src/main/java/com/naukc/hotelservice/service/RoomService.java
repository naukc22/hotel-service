package com.naukc.hotelservice.service;

import com.naukc.hotelservice.models.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {


    void add(Room room);

    boolean delete(int id);

    Optional<Room> findById(int id);

    List<Room> findAll();

}
