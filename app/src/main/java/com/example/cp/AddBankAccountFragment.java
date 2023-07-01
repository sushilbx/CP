package com.example.cp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cp.Api.RetrofitClient;
import com.example.cp.Modal.AddBankAccountModel;
import com.example.cp.Modal.SignupModel;
import com.example.cp.databinding.FragmentAddBankAccountBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddBankAccountFragment extends Fragment {
    FragmentAddBankAccountBinding b;
    SessionManager sessionManager;
    SignupModel signupModel;
    String bank_holder_name = "";
    String bank_name = "";
    String ifsc_code = "";
    String ac_number = "";
    String upi_holder_name = "";
    String upi_bank = "";
    String upi_id = "";

    public AddBankAccountFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentAddBankAccountBinding.inflate(inflater, container, false);
        sessionManager = new SessionManager(getContext());
        signupModel = sessionManager.getLoginSession();
        b.btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validation()) {
                    signUp();
                }
            }
        });



        return b.getRoot();
    }

    private void signUp() {
        Log.e("Token", "Bearer "+signupModel.token);
        Log.e("djffj", bank_holder_name);
        Log.e("djffj", bank_name);
        Log.e("djffj", ifsc_code);
        Log.e("djffj", ac_number);
        Log.e("djffj", upi_holder_name);
        Log.e("djffj", upi_bank);
        Log.e("djffj", upi_id);

        Call<AddBankAccountModel> call = RetrofitClient.getInstance().getApi().addBank("Bearer "+signupModel.token,bank_holder_name, bank_name, ifsc_code, ac_number, upi_holder_name, upi_bank, upi_id);
        call.enqueue(new Callback<AddBankAccountModel>() {
            @Override
            public void onResponse(Call<AddBankAccountModel> call, Response<AddBankAccountModel> response) {
                if (response.isSuccessful()) {
                    Log.e("sushil Signup", new Gson().toJson(response.body()));
                    if (response.body().message.equalsIgnoreCase("Professional Registration successful")) {
                        Toast.makeText(getContext(), response.body().message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), DashboardActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getContext(), "name or mobile or email has already been taken", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddBankAccountModel> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }


    boolean validation() {
        bank_holder_name = b.etAccountHolderName.getText().toString();
        bank_name = b.etBankName.getText().toString();
        ifsc_code = b.etIfscCode.getText().toString();
        ac_number = b.etAccountNumber.getText().toString();
        upi_holder_name = b.etUpiHolderName.getText().toString();
        upi_bank = b.etUpiBank.getText().toString();
        upi_id = b.etUpiId.getText().toString();


        if (b.etAccountHolderName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "bank_holder_name is required!!!!", Toast.LENGTH_SHORT).show();
            return false;

        } else if (b.etBankName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "bank_name is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (ifsc_code.isEmpty()) {
            Toast.makeText(getContext(), "ifsc_code is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etAccountNumber.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "ac_number  is required!!!!", Toast.LENGTH_SHORT).show();
                return false;

        } else if (b.etUpiHolderName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "upi_holder_name is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (b.etUpiBank.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "upi_bank is required!!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}