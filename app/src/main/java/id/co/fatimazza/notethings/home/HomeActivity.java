package id.co.fatimazza.notethings.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.addthings.AddThingsActivity;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_listof_things)
    public RecyclerView rvListOfThings;

    @BindView(R.id.tv_empty_data)
    public TextView tvEmpty;

    @BindView(R.id.fab_add)
    public FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    public void addNewThingToList (View view) {
        startActivity(new Intent(this, AddThingsActivity.class));
    }
}
