package com.app.mobiustest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BonusdetailsAdapter bonusdetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


        ServerAPI service = RetrofitClientInstance.getRetrofitInstance().create(ServerAPI.class);
        Call<List<Example>> call = service.getOfferDetails();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                //  progressDoalog.dismiss();
                getDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                //    progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataList(List<Example> examples) {

        bonusdetailsAdapter=new BonusdetailsAdapter(this,examples);
        recyclerView.setAdapter(bonusdetailsAdapter);

        //bonusdetailsAdapter.setdata(this,examples);


    }
}
