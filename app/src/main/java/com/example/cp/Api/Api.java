package com.example.cp.Api;


import com.example.cp.Modal.AddBankAccountModel;
import com.example.cp.Modal.ChangePasswordModel;
import com.example.cp.Modal.ForgotPasswordModel;
import com.example.cp.Modal.InviteModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.Modal.WalletDepositeModel;
import com.example.cp.Modal.WalletWithdrawlModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("api/add/bankDetails")
    Call<AddBankAccountModel> addBank(
            @Header("Authorization") String Authorization,
            @Field("bank_holder_name") String bank_holder_name,
            @Field("bank_name") String bank_name,
            @Field("ifsc_code") String ifsc_code,
            @Field("ac_number") String ac_number,
            @Field("upi_holder_name") String upi_holder_name,
            @Field("upi_bank") String upi_bank,
            @Field("upi_id") String upi_id
    );

    @FormUrlEncoded
    @POST("api/wallet/deposit")
    Call<WalletDepositeModel> walletDeposite(
            @Header("Authorization") String Authorization,
            @Field("amount") String amount,
            @Field("tn_ref_no") String tn_ref_no
    );

    @FormUrlEncoded
    @POST("api/user/invite")
    Call<InviteModel> inviteUser(
            @Header("Authorization") String Authorization,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("email") String email,
            @Field("refered_by") String refered_by
    );

    @FormUrlEncoded
    @POST("api/wallet/withdrawal")
    Call<WalletWithdrawlModel> walletWithdrawl(
            @Header("Authorization") String Authorization,
            @Field("amount") String amount,
            @Field("fee") String fee,
            @Field("transfer_bank") String transfer_bank,
            @Field("transfer_name") String transfer_name,
            @Field("transfer_type") String transfer_type,
            @Field("transfer_account") String transfer_account
    );

    @GET("api/user/invite/list")
    Call<SignupModel> getInviteList(
            @Header("Authorization") String Authorization
    );

    @GET("api/accountSecurity/list")
    Call<SignupModel> getSecurityList(
            @Header("Authorization") String Authorization
    );

    @FormUrlEncoded
    @POST("api/send/otp")
    Call<ForgotPasswordModel> forgotPass(
            @Header("Authorization") String Authorization,
            @Field("phone") String phone

    );

    @FormUrlEncoded
    @POST("api/change/password")
    Call<ChangePasswordModel> changePassword(
            @Header("Authorization") String Authorization,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("otp") String otp


    );


}


