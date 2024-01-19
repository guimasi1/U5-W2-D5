package com.example.U5W2D5.device;

import com.example.U5W2D5.exceptions.BadRequestException;
import com.example.U5W2D5.user.User;
import com.example.U5W2D5.user.UsersService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


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
}
