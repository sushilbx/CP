package com.example.cp.Modal;

import java.util.List;

public class WalletModel {
    public String message;
    public List<Datum> data;
    public class Datum{
        public int id;
        public String wallet_amount;
    }

}
