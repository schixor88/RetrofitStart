package com.example.retrofitapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv_data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn_1);
        tv_data = findViewById(R.id.tv_1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                //Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//
//                intent.putExtra("hello","world");
//
//                startActivity(intent);

                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create()).build();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                Call<List<Post>> call = apiInterface.getPosts(2);

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                        }

                        List<Post> posts =response.body();

                        for (Post post : posts){
                            String data = " ";
                            data+="ID: "+post.getId()+"\n";
                            data+="Body: "+post.getBody()+"\n\n";

                            tv_data.append(data);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });




            }
        });




    }
}
