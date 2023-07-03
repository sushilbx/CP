package com.example.cp.Modal;

import java.util.Date;
import java.util.List;

public class DepositeListModel {
    public String message;
    public List<Datum> data;
    public class Datum{
        public String id;
        public int user_id;
        public String transaction_id;
        public String tn_ref_no;
        public String name;
        public String mobile;
        public String amount;
        public String payout_mode;
        public String status;
        public String created_at;
        public Date updated_at;
    }

}
