package com.example.U5W2D5.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class DevicesService {
    @Autowired
    DevicesDAO devicesDAO;

    public Page<Device> getAuthors(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return devicesDAO.findAll(pageable);
    }
/*
    public Device save(NewDeviceDTO device) {



    }*/
}
