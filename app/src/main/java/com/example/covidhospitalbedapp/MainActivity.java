package com.example.covidhospitalbedapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covidhospitalbedapp.RequestedValues.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button LoginButton;
    Button SignUpButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginButton = findViewById(R.id.loginbutton);
        SignUpButton = findViewById(R.id.signupbutton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlelogindialog();
            }
        });

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlesignupdialog();
            }
        });
    }


    private void handlelogindialog() {

        View view = getLayoutInflater().inflate(R.layout.login_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button loginbutton  = view.findViewById(R.id.loginbutton);
        final EditText emailedit = view.findViewById(R.id.emailedit);
        final EditText passwordedit = view.findViewById(R.id.passwordedit);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Logging in....", Toast.LENGTH_SHORT).show();

                HashMap<String,String> map = new HashMap<>();

                map.put("email",emailedit.getText().toString());
                map.put("password",passwordedit.getText().toString());

                //  call for post request to server
                //Call<LoginResult> call = retrofitInterface.excuteLogin(map);
                Call<LoginResult> call = APIcalls.getRetrofitInterface().excuteLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if(response.code()==200){

                            LoginResult result = response.body();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                            builder1.setTitle(result.getName());
                            builder1.setMessage(result.getEmail());

                            builder1.show();
                        }
                        else if(response.code()==404){
                            Toast.makeText(MainActivity.this,"Wrong credentials",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.e("Error",t.getMessage());
                    }
                });

            }
        });

    }


    private void handlesignupdialog(){

        View view = getLayoutInflater().inflate(R.layout.sign_up_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).show();

        Button signupbutton  = view.findViewById(R.id.signupbutton);
        final EditText nameedit = view.findViewById(R.id.namedit);
        final EditText emailEditText = view.findViewById(R.id.emailedit);
        final EditText passwordedit = view.findViewById(R.id.passwordedit);

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "Signing up....", Toast.LENGTH_SHORT).show();

                HashMap<String,String> map = new HashMap<>();

                map.put("name",nameedit.getText().toString());
                map.put("email",emailEditText.getText().toString());
                map.put("password",passwordedit.getText().toString());

                //  call for post request to server
                //Call<Void> call = retrofitInterface.excuteSignup(map);

                Call<Void> call = APIcalls.getRetrofitInterface().excuteSignup(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if(response.code()==200){
                            Toast.makeText(MainActivity.this, "Signed up successfully", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code()==400){
                            Toast.makeText(MainActivity.this, "This email is already registered", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                        Log.e("Error",t.getMessage());
                    }
                });

            }
        });

    }
}