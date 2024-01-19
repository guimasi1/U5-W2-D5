package com.example.U5W2D5.device;

import com.example.U5W2D5.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "devices")
public class Device {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue
    private UUID uuid;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
