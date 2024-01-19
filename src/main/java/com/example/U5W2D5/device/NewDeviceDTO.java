package com.example.U5W2D5.device;

import com.example.U5W2D5.user.User;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record NewDeviceDTO(
        String type,
        String status,
        UUID userUUID) {
}
