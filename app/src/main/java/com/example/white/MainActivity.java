package com.example.white;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private EditText usertxt,pwdtxt;
    private String Username,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btnLogin);
        usertxt=findViewById(R.id.username);
        pwdtxt=findViewById(R.id.password);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Username=usertxt.getText().toString();
                Password=pwdtxt.getText().toString();
                String authToken= createAuthToken(Username,Password);
                checkLoginDetails(authToken);
            }
        });
    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit=RetrofitClient.getRetrofitInstance();
        final InterfaceApi api=retrofit.create(InterfaceApi.class);
        Call<String> call = api.checkLogin(authToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body() !=null){
                    if(response.body().matches("success")){
                        Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Unknown error occured",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }

    private String createAuthToken(String username, String password) {
        byte [] data =new byte[0];
        try{
            data=(username + ":" +password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return "Basic"+ Base64.encodeToString(data,Base64.NO_WRAP);
    }
}