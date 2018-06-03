package id.co.fatimazza.notethings.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.co.fatimazza.notethings.NoteThings;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.addthings.AddThingsActivity;
import id.co.fatimazza.notethings.base.BaseActivity;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.Things;

public class HomeActivity extends BaseActivity implements HomeContract.View, HomeAdapter.ItemListener {

    public static final String EXTRA_IS_ADD_NEW = "is_add_new";

    @BindView(R.id.rv_listof_things)
    public RecyclerView rvListOfThings;

    @BindView(R.id.tv_empty_data)
    public TextView tvEmpty;

    @BindView(R.id.fab_add)
    public FloatingActionButton fabAdd;

    private HomeAdapter homeAdapter;

    private HomePresenter homePresenter;

    private DaoSession daoSession;

    private List<Things> listOfThings;

    boolean isAddNew = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();

        initDaoSession();
        initPresenter();
        bindViewToPresenter();

        loadDataThings();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    private void initAdapter() {
        homeAdapter = new HomeAdapter(HomeActivity.this, this);

        rvListOfThings.setLayoutManager(new LinearLayoutManager(this));
        rvListOfThings.setAdapter(homeAdapter);
    }

    private void initDaoSession() {
        daoSession = ((NoteThings) getApplication()).getDaoSession();
    }

    private void initPresenter() {
        homePresenter = new HomePresenter(daoSession);
    }

    private void bindViewToPresenter() {
        homePresenter.setView(this);
    }

    private void loadDataThings() {
        listOfThings = new ArrayList<>();
        listOfThings = daoSession.getThingsDao().loadAll();

        homeAdapter.updateListOfThings(listOfThings);
    }

    public void addNewThingToList (View view) {
        isAddNew = true;
        Intent intent = new Intent(this, AddThingsActivity.class);
        intent.putExtra(EXTRA_IS_ADD_NEW, isAddNew);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataThings();
    }

    @Override
    public void onItemClick(int position) {
        isAddNew = false;

        Things things = homeAdapter.getListOfThings().get(position);
        Toast.makeText(this,
            "Selected data: " +things.getId() +" " +things.getName() +" " +things.getQuantity(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, AddThingsActivity.class);
        intent.putExtra(EXTRA_IS_ADD_NEW, isAddNew);
        startActivity(intent);
    }
}
