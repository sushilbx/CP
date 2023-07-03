package com.example.cp.Modal;

import java.util.List;

public class PlateformModel {
    public String message;
    public List<Datum> data;
    public class Datum{
        public int id;
        public String bet_no;
        public String bet_start_time;
        public String bet_end_time;
        public String selection;
        public String bet_date;
        public String contract_money;
        public String status;
        public String manual;
    }

}
