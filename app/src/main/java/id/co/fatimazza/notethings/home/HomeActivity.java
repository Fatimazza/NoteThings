package id.co.fatimazza.notethings.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.addthings.AddThingsActivity;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView rvListOfThings;
    private TextView tvEmpty;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        rvListOfThings = findViewById(R.id.rv_listof_things);
        tvEmpty = findViewById(R.id.tv_empty_data);
        fabAdd = findViewById(R.id.fab_add);
    }

    public void addNewThingToList (View view) {
        startActivity(new Intent(this, AddThingsActivity.class));
    }
}
