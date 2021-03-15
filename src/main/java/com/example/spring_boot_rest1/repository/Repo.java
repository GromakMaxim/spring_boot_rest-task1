package com.example.spring_boot_rest1.repository;

import com.example.spring_boot_rest1.model.User;

public interface Repo {
    void save(User user);
    String show();
    void deleteItem(String nickname);
}
