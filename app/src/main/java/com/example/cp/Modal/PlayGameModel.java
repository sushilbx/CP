package com.example.cp.Modal;

import java.util.List;

public class PlayGameModel {
    public PlayGame data;

    public class PlayGame{
        public UserDeatil user_deatil;
        public BetRecordList bet_record_list;
        public LasteBetRecord laste_bet_record;
        public String bet_no;
        public String bet_start_time;
        public String bet_end_time;
    }


    public class BetRecordList{
        public int current_page;
        public List<Object> data;
        public String first_page_url;
        public Object from;
        public int last_page;
        public String last_page_url;
        public Object next_page_url;
        public String path;
        public int per_page;
        public Object prev_page_url;
        public Object myto;
        public int total;
    }



    public class LasteBetRecord{
        public int current_page;
        public List<LatestBet> data;
        public String first_page_url;
        public int from;
        public int last_page;
        public String last_page_url;
        public String next_page_url;
        public String path;
        public int per_page;
        public Object prev_page_url;
        public int myto;
        public int total;
    }

    public class UserDeatil{
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
        public String password;
        public Object status;
        public String created_at;
        public String updated_at;
    }
    public class LatestBet{
        public int id;
        public String selection;
        public String bet_date;
        public String contract_money;
        public String status;
        public String manual;
        public String bet_start_time;
        public String bet_end_time;
    }
}
