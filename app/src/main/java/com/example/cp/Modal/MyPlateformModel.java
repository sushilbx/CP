package com.example.cp.Modal;

import java.util.List;

public class MyPlateformModel {
    public String message;
    public List<Datum> data;
    public class Datum{
        public int id;
        public int user_id;
        public String bet_id;
        public String contract_money;
        public String delivery;
        public String fee;
        public String select;
        public String result;
        public String bet_amount;
        public String amount;
        public String status;
        public String created_at;
        public String updated_at;
    }
}
