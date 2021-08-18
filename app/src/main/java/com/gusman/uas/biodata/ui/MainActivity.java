package com.gusman.uas.biodata.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gusman.uas.biodata.R;
import com.gusman.uas.biodata.databinding.ActivityMainBinding;
import com.gusman.uas.biodata.helper.DBHelper;
import com.gusman.uas.biodata.ui.form.AddBioActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BioDataAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        adapter = new BioDataAdapter(this, new ArrayList<>());
        binding.contentMain.rvBioData.setLayoutManager(new LinearLayoutManager(this));
        binding.contentMain.rvBioData.setAdapter(adapter);

        binding.fab.setOnClickListener(view -> startActivity(new Intent(this, AddBioActivity.class)));
        binding.fab.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddBioActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        DBHelper dbHelper = new DBHelper(this);
        adapter.setDataList(dbHelper.getAllBioData());
        adapter.notifyDataSetChanged();
    }

}