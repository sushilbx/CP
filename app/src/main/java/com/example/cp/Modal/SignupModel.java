package com.example.cp.Modal;

public class SignupModel {
    public boolean status;
    public int code;
    public String message;
    public User user;
    public String token;


    public class User {
        public String name;
        public String email;
        public String phone;
        public String refer_id;
        public int id;
    }
}
