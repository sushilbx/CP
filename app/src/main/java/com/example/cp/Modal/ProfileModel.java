package com.example.cp.Modal;

import java.util.List;

public class ProfileModel {
    public String message;
    public List<Datum> data;
    public class Datum{
        public int id;
        public String user_type;
        public String name;
        public String phone;
        public String email;
        public String wallet_amount;
        public String refer_id;
        public String refered_by;
        public String upi_holder_name;
        public String upi_bank;
        public String upi_id;
        public String bank_holder_name;
        public String bank_name;
        public String ac_number;
        public String ifsc_code;
    }

}
