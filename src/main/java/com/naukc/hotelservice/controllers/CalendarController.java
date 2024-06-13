package com.naukc.hotelservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/calendar")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
public class CalendarController {

//    @GetMapping(value = "/getAllRooms", produces = "application/json")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Object> getAllRooms() {
//        String json = "[{\"id\":1,\"roomNumber\":\"101\",\"roomName\":\"Room1\"}," +
//                "{\"id\":2,\"roomNumber\":\"102\",\"roomName\":\"Room2\"}," +
//                "{\"id\":2,\"roomNumber\":\"103\",\"roomName\":\"Room3\"}" +
//                ",{\"id\":2,\"roomNumber\":\"1044\",\"roomName\":\"Room4\"}]";
//        return new ResponseEntity<>(json, HttpStatus.OK);
//    }

    @GetMapping(value = "/getBookingsByMonth", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> getBookingsByMonth(@RequestParam int month, @RequestParam int year) {
        System.out.println(month);
        System.out.println(year);
        String result = "";
        if (month == 2) {
            result = "[{\"day\":\"3\",\"type\":\"in\",\"roomName\":\"Room1\"}," +
                    "{\"day\":\"4\",\"type\":\"full\",\"roomName\":\"Room1\"},{\"day\":\"5\",\"type\":\"out\",\"roomName\":\"Room1\"},{\"day\":\"5\",\"type\":\"in\",\"roomName\":\"Room1\"},{\"day\":\"6\",\"type\":\"out\",\"roomName\":\"Room1\"}]";
        }
//        } else {
//            result = "[{\"monthDay\":3,\"roomName\":\"Room1\"}," +
//                    "{\"monthDay\":9,\"roomName\":\"Room2\"}," +
//                    "{\"monthDay\":11,\"roomName\":\"Room3\"}, {\"monthDay\":16,\"roomName\":\"Room3\"},{\"monthDay\":18,\"roomName\":\"Room3\"}]";
//        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
