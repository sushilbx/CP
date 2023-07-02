package com.example.cp.Modal;

import java.util.Date;

public class InviteModel {
    public String message;
    public Invite data;
    public class Invite{
        public String user_type;
        public String name;
        public String phone;
        public String email;
        public String refer_id;
        public String refered_by;
        public String status;
        public Date updated_at;
        public Date created_at;
        public int id;
    }

}
