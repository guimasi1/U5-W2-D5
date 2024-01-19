package com.example.U5W2D5.device;

import com.example.U5W2D5.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record NewDeviceDTO(
        @NotEmpty
        @Size(min = 3, max = 20, message = "Il nome del tipo di dispositivo deve essere di almeno 3 caratteri e massimo 20.")
        String type,
        @NotEmpty
        @Size(min = 3, max = 20, message = "Lo stato del dispositivo deve essere di almeno 3 caratteri e massimo 20.")
        String status,
        UUID userUUID) {
}
