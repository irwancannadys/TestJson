package com.example.qodri.testjson;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.qodri.testjson.model.Example;
import com.example.qodri.testjson.rest.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//    ROOT_URL dari web service
    public static final String ROOT_URL = "http:/api.teknorial.com";

//    definisi tampilan

    private TextView txt_id;
    private TextView txt_nama;
    private TextView txt_email;
    private TextView txt_alamat;
    private TextView txt_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_nama = (TextView) findViewById(R.id.txt_nama);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_alamat = (TextView) findViewById(R.id.txt_alamat);
        txt_status = (TextView) findViewById(R.id.txt_status);

//        memanggil method untuk memanggil data
        getData();


    }

    private void getData(){


        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Tunggu Sebentar..", false,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RestApi service = retrofit.create(RestApi.class);
        Call<Example> call = service.getDataAdmin();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                try{
                    loading.dismiss();

                    String id = response.body().getAdmin().getId().toString();
                    String nama = response.body().getAdmin().getNama();
                    String email = response.body().getAdmin().getEmail();
                    String alamat = response.body().getAdmin().getAlamat();
                    String status = response.body().getAdmin().getStatus();

                    txt_id.setText("ID : "+ id);
                    txt_nama.setText("Nama : "+nama);
                    txt_email.setText("Email : "+email);
                    txt_alamat.setText("Alamat : "+alamat);
                    txt_status.setText("Status : "+status);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
