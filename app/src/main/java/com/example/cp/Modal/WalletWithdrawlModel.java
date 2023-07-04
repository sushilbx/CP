package com.example.cp.Modal;

import java.util.Date;
import java.util.List;

public class WalletWithdrawlModel {
    public String message;

    public List<Withdrawl> data ;
    public class Withdrawl{
        public String transaction_id;
        public int user_id;
        public String name;
        public String mobile;
        public String ifsc;
        public String amount;
        public String transfer_account;
        public String fee;
        public String transfer_bank;
        public String transfer_name;
        public String transfer_type;
        public String payout_mode;
        public String status;
        public Date updated_at;
        public String created_at;
        public String id;
    }

}
