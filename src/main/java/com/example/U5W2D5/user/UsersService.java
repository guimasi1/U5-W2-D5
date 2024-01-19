package com.example.U5W2D5.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.U5W2D5.exceptions.BadRequestException;
import com.example.U5W2D5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@Service
public class UsersService {
    @Autowired
    UsersDAO usersDAO;

    @Autowired
    Cloudinary cloudinary;

    public Page<User> getUsers(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return usersDAO.findAll(pageable);
    }

    public User save(NewUserDTO user) {
        usersDAO.findByEmail(user.email()).ifPresent(user1 -> {
            throw new BadRequestException("Email " + user.email() + " già in uso");
        });

        User newUser = new User();
        newUser.setUsername(user.username());
        newUser.setSurname(user.surname());
        newUser.setName(user.name());
        newUser.setEmail(user.email());
        newUser.setAvatarUrl("https://ui-avatars.com/api/?name=" + user.name() + "+" + user.surname());
        return usersDAO.save(newUser);
    }

    public User findById(UUID id) {
        return usersDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(UUID uuid, User body) {
        User found = this.findById(uuid);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setUsername(body.getUsername());
        found.setAvatarUrl(body.getAvatarUrl());
        return usersDAO.save(found);
    }

    public void deleteById(UUID uuid) {
        User found = this.findById(uuid);
        usersDAO.delete(found);
    }

    public String uploadPicture(MultipartFile file) throws IOException {

        String url = (String) cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        return url;
    }

}
