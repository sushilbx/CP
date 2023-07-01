package com.example.cp.Api;


import com.example.cp.Modal.AddBankAccountModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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


//    @FormUrlEncoded
//    @POST("professional/login")
//    Call<SessionModel> login(
//            @Field("email") String email,
//            @Field("password") String password
//    );
//
//    @GET("professional/patients")
//    Call<PatientModel> patient(
//            @Header("Authorization") String Authorization
//    );
//
//    @GET("professional/patient-details/{patient_id}")
//    Call<PatientDetailsModel> patientDetails(
//            @Header("Authorization") String Authorization,
//            @Path("patient_id") String patient_id
//    );
//
//    @GET("professional/doctor-wallet")
//    Call<PayoutModel> payout(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @GET("professional/doctor-wallet?page=2")
//    Call<PayoutModel> payout2(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @GET("professional/doctor-wallet?page=3")
//    Call<PayoutModel> payout3(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @GET("professional/doctor-wallet?page=4")
//    Call<PayoutModel> payout4(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @GET("professional/notification-list")
//    Call<NotificationModel> notification(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @GET("professional/profile-details")
//    Call<ProfileModel> profile(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @FormUrlEncoded
//    @POST("professional/update-profile")
//    Call<ProfileModel> editProfile(
//            @Header("Authorization") String auth,
//            @Field("email") String email,
//            @Field("name") String name,
//            @Field("phone") String phone,
//            @Field("gender") String gender,
//            @Field("date_of_birth") String date_of_birth,
//            @Field("bank_name") String bank_name,
//            @Field("ifcs_code") String ifcs_code,
//            @Field("account_number") String account_number,
//            @Field("biography") String biography,
//            @Field("clinic_name") String clinic_name,
//            @Field("clinic_address") String clinic_address,
//            @Field("address_one") String address_one,
//            @Field("address_two") String address_two,
//            @Field("pin_code") String pin_code,
//            @Field("service") String service,
//            @Field("service") String specislization);
//
//    @FormUrlEncoded
//    @POST("professional/create-prescription")
//    Call<ProfileModel> createPrescription(
//            @Header("Authorization") String auth,
//            @Field("user_id") String user_id,
//            @Field("patient_id") String patient_id,
//            @Field("appointment_id") String appointment_id,
//            @Field("disease") String disease,
//            @Field("medicine") String medicine,
//            @Field("dosage") String dosage,
//            @Field("advice") String advice,
//            @Field("note") String note,
//            @Field("next_consult_date") String next_consult_date
//
//    );
//
//    @FormUrlEncoded
//    @POST("professional/update-prescription")
//    Call<ProfileModel> updatePrescription(
//            @Header("Authorization") String auth,
//            @Field("prescription_id") String prescription_id,
//            @Field("disease") String disease,
//            @Field("medicine") String medicine,
//            @Field("dosage") String dosage,
//            @Field("advice") String advice,
//            @Field("note") String note,
//            @Field("next_consult_date") String next_consult_date
//
//    );
//
//    @DELETE("professional/delete-prescription/{id}")
//    Call<ProfileModel> delete(
//            @Header("Authorization") String auth,
//            @Path("id") String id
//    );
//
//    @FormUrlEncoded
//    @POST("professional/update-password")
//    Call<ProfileModel> updatePassword(
//            @Header("Authorization") String auth,
//            @Field("old_password") String old_password,
//            @Field("password") String password,
//            @Field("password_confirmation") String password_confirmation
//    );
//
//    @FormUrlEncoded
//    @POST("professional/spotlight-request")
//    Call<ProfileModel> spot(
//            @Header("Authorization") String auth,
//            @Field("amount") String amount
//
//    );
//
//    @FormUrlEncoded
//    @POST("professional/accept-notification")
//    Call<AcceptNotificationModel> accept(
//            @Header("Authorization") String auth,
//            @Field("status_type") String status_type,
//            @Field("group_therapy_id") String group_therapy_id,
//            @Field("appointment_id") String appointment_id
//
//    );
//
//    @GET("professional/blog-list")
//    Call<BlogModel> blog(
//            @Header("Authorization") String Authorization
//    );
//
//    @GET("professional/dashboard-details")
//    Call<DashboardModel> dashboard(
//            @Header("Authorization") String Authorization
//
//    );
//
//    @GET("professional/appointments")
//    Call<AppointmentModel> apoint(
//            @Header("Authorization") String Authorization,
//            @Query("appointment_type") String appointment_type,
//            @Query("appointment_date") String appointment_date
//
//    );
//
//    @FormUrlEncoded
//    @POST("professional/review-reply")
//    Call<ProfileModel> reviewReply(
//            @Header("Authorization") String auth,
//            @Field("patient_id") String patient_id,
//            @Field("review_id") int review_id,
//            @Field("comments") String comments
//    );
//
//    @GET("professional/country-list")
//    Call<CountryModel> country(
//    );
//
//    @GET("professional/state-list/{country_id}")
//    Call<StateModel> state(
//            @Path("country_id") String country_id
//    );
//
//    @GET("professional/city-list/{state_id}")
//    Call<CityModel> city(
//            @Path("state_id") String state_id
//    );
//
//    @FormUrlEncoded
//    @POST("professional/create-working-hour")
//    Call<NotificationModel> slotPost(
//            @Header("Authorization") String auth,
//            @Field("slots[0]") String slotsFrom
//
//    );
//
//    @GET("professional/services-list")
//    Call<ServiceModel> subCat(
//
//    );
//
//    @FormUrlEncoded
//    @POST("professional/forget-password")
//    Call<ProfileModel> forgot(
//            @Field("email") String email
//    );
//
//    @FormUrlEncoded
//    @POST("professional/reset-password")
//    Call<ProfileModel> reset(
//            @Field("email") String email,
//            @Field("password") String password,
//            @Field("token") String token
//    );
//
//   /* @FormUrlEncoded
//    @POST("professional/create-profile-first")
//    Call<ProfileModel> createFirst(
//            @Field("doctor_id") String doctor_id,
//            @Field("subcategory_ids") JSONArray subcategory_ids
//
//    );*/
//    @POST("professional/create-profile-first")
//    Call<ProfileModel> createFirst(@Body CreateProfileBody createProfileBody);
//
//
//    @FormUrlEncoded
//    @POST("professional/create-profile-second")
//    Call<ProfileModel> createSecond(
//            @Field("doctor_id") String doctor_id,
//            @Field("consultation_type_ids[0]") String consultation_type_ids,
//            @Field("education[0][degree]") String education,
//            @Field("education[0][college]") String education_college,
//            @Field("education[0][year_of_completion]") String education_complete,
//            @Field("education[0][proof_of_document]") String education_proof,
//            @Field("registration[0][number]") String registration_number,
//            @Field("registration[0][council]") String registration_council,
//            @Field("registration[0][year]") String registration_year,
//             @Field("signature_proof") String signature_proof,
//            @Field("description") String description
//    );

}


