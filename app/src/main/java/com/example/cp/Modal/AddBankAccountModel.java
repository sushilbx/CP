package com.example.cp.Modal;

public class AddBankAccountModel {
    public String message;
    public Data data;

    public class Data {
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
