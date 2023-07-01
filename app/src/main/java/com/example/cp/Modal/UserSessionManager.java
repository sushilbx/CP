package com.example.cp.Modal;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class UserSessionManager {



    SharedPreferences pref; // shared preferences reference
    SharedPreferences.Editor editor; //editor reference for shared preferences
    Context _context,ctx;
    int PRIVATE_MODE = 0; // shared prefs mode
    public static final String MY_PREFS_NAME1 = "MyPrefsFile"; //shared preferences file
    private static final String IS_USER_LOGED_IN = Keys.isUserLoggedIn; //all shared preferences keys
    public static final String KEY_NAME = Keys.profileName; // taking key name from keys.class
    public static final String KEY_Email = Keys.profileName; // taking key name from keys.class

    public static final String KEY_PROFILE_PIC = com.example.cp.Modal.Keys.profilePic;  // taking key profile from keys.class

    public static final String KEY_Phone = Keys.profileName; // taking key name from keys.class
    public static final String KEY_USER_ID = Keys.userId;  // taking key userId from keys.class

    public static final String KEY_GENDER = com.example.cp.Modal.Keys.gender;  // taking key gender from keys.class

    public static final String KEY_AUTO_LOGIN = Keys.autoLogin;  // taking key if auto login or not from keys.class
    public UserSessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(MY_PREFS_NAME1,PRIVATE_MODE);
    }
    // create login session if user checks auto login
    public void createUserLoginSession(String gender,String name,String email,String phone,String profile_pic,Context ctx,boolean checkeedOrNot){
        this.ctx = ctx; // getting context from main activity
        editor = ctx.getSharedPreferences(MY_PREFS_NAME1,PRIVATE_MODE).edit(); // create shared preferences using instance
        editor.putBoolean(IS_USER_LOGED_IN,true);         //storing login value as true
        //storing name,gender,userid
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_Email,email);
        editor.putString(KEY_Phone,phone);
        editor.putString(KEY_GENDER,gender);
        editor.putString(KEY_PROFILE_PIC,profile_pic);
//        editor.putString(KEY_USER_ID,userId);
        editor.putBoolean(KEY_AUTO_LOGIN,checkeedOrNot);

        editor.apply(); // commit changes
    }

    public void updateProfilePic(String pic,Context ctx){
        this.ctx = ctx;
        editor = ctx.getSharedPreferences(MY_PREFS_NAME1,PRIVATE_MODE).edit(); // create shared preferences using instance
        editor.putString(KEY_PROFILE_PIC,pic);
        editor.apply();
    }



    /*
     * checkLogin method will checks the user login status
     * if it is false the Main activity is called
     * else Home activity is called
     * */
    public boolean checkLogin(String activity){
        //check login status
        if(!this.isUserLoggedIn()){
            // user is not logged in redirect to login activity
//            Intent intent = new Intent(_context, LoginOrRegisterSectionActivity.class);
//            //clear all activities from the stack
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(intent);
            return true; // if user is not logged in it returns true else returns false
        }else{
            if(userIsCheckedAutoLogin()){
                return false;
            }else{
                if(activity==null){
                    editor = _context.getSharedPreferences(MY_PREFS_NAME1,PRIVATE_MODE).edit();
                    //clearing shared preferences
                    editor.clear();
                    editor.commit();
                    // user is not logged in redirect to login activity
//                    Intent intent = new Intent(_context, LoginOrRegisterSectionActivity.class);
//                    //clear all activities from the stack
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    _context.startActivity(intent);
                    return true; // if user is not logged in it returns true else returns false
                }else{
                    return false;
                }

            }

        }
    }

    // check if auto login is there or not
    public boolean userIsCheckedAutoLogin() {

        return pref.getBoolean(KEY_AUTO_LOGIN,false);
    }


    public boolean isUserLoggedIn() {
        return pref.getBoolean(IS_USER_LOGED_IN, false);
    }
    /*
    get stored session data
     */

    public HashMap<String,String> getUserDetails(){
        // using hashmap to store login credentials
        HashMap<String,String> user = new HashMap<String, String>();
        user.put(KEY_NAME,pref.getString(KEY_NAME,null));
        user.put(KEY_Email,pref.getString(KEY_Email,null));
        user.put(KEY_Phone,pref.getString(KEY_Phone,null));
        user.put(KEY_USER_ID,pref.getString(KEY_USER_ID,null));
        user.put(KEY_PROFILE_PIC,pref.getString(KEY_PROFILE_PIC,null));

        return user; // returning user
    }

    /*
    once user pressed logogut will clear session details
     */

    public void logOutUser(){
        editor = _context.getSharedPreferences(MY_PREFS_NAME1,PRIVATE_MODE).edit();
        //clearing shared preferences
        editor.clear();
        editor.commit();
//        Intent intent = new Intent(_context,LoginOrRegisterSectionActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        _context.startActivity(intent);
    }

    //backbutton pressed logout
    public void logOutUserWhenBackpressed(){
        editor = _context.getSharedPreferences(MY_PREFS_NAME1,PRIVATE_MODE).edit();
        //clearing shared preferences
        editor.clear();
        editor.commit();
    }
}

