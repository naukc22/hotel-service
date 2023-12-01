package com.example.hotelservice.serviceImp;

import com.example.hotelservice.models.Room;
import com.example.hotelservice.repository.RoomRepository;
import com.example.hotelservice.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {

    RoomRepository roomRepository;

    @Autowired
    public RoomServiceImp(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void add(Room room) {
        roomRepository.save(room);
    }

    @Override
    public boolean delete(int id) {
        roomRepository.deleteById(id);
        return true;
    }


}
