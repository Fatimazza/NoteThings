package id.co.fatimazza.notethings.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.addthings.AddThingsActivity;
import id.co.fatimazza.notethings.base.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeContract.View {

    @BindView(R.id.rv_listof_things)
    public RecyclerView rvListOfThings;

    @BindView(R.id.tv_empty_data)
    public TextView tvEmpty;

    @BindView(R.id.fab_add)
    public FloatingActionButton fabAdd;

    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    private void initView() {
        homeAdapter = new HomeAdapter(this);

        rvListOfThings.setLayoutManager(new LinearLayoutManager(this));
        rvListOfThings.setAdapter(homeAdapter);
        homeAdapter.notifyDataSetChanged();
    }

    public void addNewThingToList (View view) {
        startActivity(new Intent(this, AddThingsActivity.class));
    }
}
