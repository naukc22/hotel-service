package com.naukc.hotelservice.controllers;

import com.google.gson.Gson;
import com.naukc.hotelservice.models.Room;
import com.naukc.hotelservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/room")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping (value = "/getAllRooms", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getAllRooms() {
        List<Room> list = roomService.findAll();
        String json = new Gson().toJson(list);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }


    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        roomService.add(room);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public boolean delete(@PathVariable int id) {
        return roomService.delete(id);
    }


}
