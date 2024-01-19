package com.example.U5W2D5.device;

import com.example.U5W2D5.exceptions.BadRequestException;
import com.example.U5W2D5.user.NewUserDTO;
import com.example.U5W2D5.user.User;
import com.example.U5W2D5.user.UsersResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    DevicesService devicesService;

    @GetMapping
    public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String id) {
        return devicesService.getDevices(page,size,id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DevicesRespondeDTO create(@RequestBody @Validated NewDeviceDTO device, BindingResult validation) {
        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload.");
        } else {
            Device newDevice = devicesService.save(device);
            return new DevicesRespondeDTO(newDevice.getId());
        }
    }

    @PutMapping("/{uuid}")
    public Device updateById(@PathVariable UUID uuid, @RequestBody NewDeviceDTO body) {
        return devicesService.findByIdAndUpdate(uuid, body);
    }

    @DeleteMapping("/{uuid}")
    public void deleteById(@PathVariable UUID uuid) {
        devicesService.deleteById(uuid);
    }

    @GetMapping("/{id}")
    public Device getDeviceById(@PathVariable UUID id) {
        return devicesService.findById(id);
    }

}
