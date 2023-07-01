package com.example.cp.Modal;

import java.util.Date;

public class WalletDepositeModel {
    public String message;
    public Wallet data;

    public class Wallet {
        public String transaction_id;
        public String tn_ref_no;
        public int user_id;
        public String name;
        public String mobile;
        public String amount;
        public String payout_mode;
        public String status;
        public Date updated_at;
        public Date created_at;
        public int id;


    }
}
