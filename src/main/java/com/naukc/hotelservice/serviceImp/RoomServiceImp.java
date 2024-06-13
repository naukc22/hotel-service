package com.naukc.hotelservice.serviceImp;

import com.naukc.hotelservice.models.Room;
import com.naukc.hotelservice.repository.RoomRepository;
import com.naukc.hotelservice.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Room> findById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
