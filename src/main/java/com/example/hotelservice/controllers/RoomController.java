package com.example.hotelservice.controllers;

import com.example.hotelservice.models.Room;
import com.example.hotelservice.repository.RoomRepository;
import com.example.hotelservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    /**
     * Создает новый гост.номер
     * @param room - гост.номер для создания
     */
    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        roomService.add(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * Удаляет номер с заданным ID
     * @param id - id номера, который нужно удалить
     * @return - true если номер был удален, иначе false
     */
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public boolean delete(@PathVariable int id) {
        return roomService.delete(id);
    }


}
