package id.co.fatimazza.notethings.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.co.fatimazza.notethings.NoteThings;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.login.LoginActivity;
import id.co.fatimazza.notethings.managethings.ManageThingsActivity;
import id.co.fatimazza.notethings.base.BaseActivity;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.Things;

public class HomeActivity extends BaseActivity implements HomeContract.View, HomeAdapter.ItemListener {

    public static final String EXTRA_IS_ADD_NEW = "is_add_new";

    public static final String EXTRA_THING_ID = "thing_id";

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

    public static boolean isLogin = false;

    private MenuItem menuLogin;

    private MenuItem menuLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();

        initDaoSession();
        initPresenter();
        bindViewToPresenter();

        loadDataThings();
        checkEmptyData();
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

    private void checkEmptyData() {
        tvEmpty.setVisibility(listOfThings.size()==0 ? View.VISIBLE : View.GONE);
        rvListOfThings.setVisibility(listOfThings.size()==0 ? View.GONE : View.VISIBLE);
    }

    public void addNewThingToList (View view) {
        isAddNew = true;

        if (isLogin) {
            Intent intent = new Intent(this, ManageThingsActivity.class);
            intent.putExtra(EXTRA_IS_ADD_NEW, isAddNew);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please Login to Add data", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataThings();
        checkEmptyData();

        if (null != menuLogin) menuLogin.setVisible(isLogin ? false : true);
        if (null != menuLogout) menuLogout.setVisible(isLogin ? true : false);
    }

    @Override
    public void onItemClick(int position) {
        isAddNew = false;

        if (isLogin) {
            Things things = homeAdapter.getListOfThings().get(position);
            Intent intent = new Intent(this, ManageThingsActivity.class);
            intent.putExtra(EXTRA_IS_ADD_NEW, isAddNew);
            intent.putExtra(EXTRA_THING_ID, things.getId());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please Login to Manage data", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuLogin = menu.findItem(R.id.action_login);
        menuLogout = menu.findItem(R.id.action_logout);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            case R.id.action_logout:
                isLogin = false;
                menuLogin.setVisible(true);
                menuLogout.setVisible(false);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
