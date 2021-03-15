package com.example.spring_boot_rest1.model;

import java.util.List;

public class User {
    private String nickname;
    private String password;
    private List<Authorities> authorities;

    public User() {
    }

    public User(String nickname, String password, List<Authorities> authorities) {
        this.nickname = nickname;
        this.password = password;
        this.authorities = authorities;
    }

    //getters and setters

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
