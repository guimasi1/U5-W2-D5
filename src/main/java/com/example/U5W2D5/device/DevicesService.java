package com.example.U5W2D5.device;

import com.example.U5W2D5.exceptions.NotFoundException;
import com.example.U5W2D5.user.User;
import com.example.U5W2D5.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.event.PaintEvent;
import java.util.UUID;


@Service
public class DevicesService {
    @Autowired
    DevicesDAO devicesDAO;

    @Autowired
    UsersService usersService;

    public Page<Device> getDevices(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return devicesDAO.findAll(pageable);
    }
    public Device save(NewDeviceDTO device) {
        User user = usersService.findById(device.userUUID());
        Device newDevice = new Device();
        newDevice.setType(device.type());
        newDevice.setUser(user);
        newDevice.setStatus(device.status());
        return devicesDAO.save(newDevice);
    }

    public Device findById(UUID id) {
        return devicesDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device findByIdAndUpdate(UUID id, NewDeviceDTO body) {
        Device found = this.findById(id);
        User user = usersService.findById(body.userUUID());
        found.setStatus(body.status());
        found.setType(body.type());
        found.setUser(user);

        return devicesDAO.save(found);
    }

    public void deleteById(UUID uuid) {
        Device found = this.findById(uuid);
        devicesDAO.delete(found);
    }

    public Page<Device> getDevices(String type, String status, int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        if(type == null && status == null) return devicesDAO.findAll(pageable);
        if(type == null) return devicesDAO.findByStatus(status,pageable);
        if(status == null) return devicesDAO.findByType(type,pageable);
        return devicesDAO.findByTypeAndStatus(type,status,pageable);

    }
}
