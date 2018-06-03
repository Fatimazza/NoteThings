package id.co.fatimazza.notethings.addthings;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import id.co.fatimazza.notethings.NoteThings;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.base.BaseActivity;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.home.HomeActivity;

public class AddThingsActivity extends BaseActivity implements AddThingsContract.View {

    @BindView(R.id.et_nameof_thing)
    public EditText etNameOfThing;

    @BindView(R.id.sp_supplier)
    public Spinner spSupplier;

    @BindView(R.id.et_quantity)
    public EditText etQuantity;

    @BindView(R.id.et_date)
    public EditText etDate;

    @BindView(R.id.fab_remove)
    public FloatingActionButton fabRemove;

    private AddThingsPresenter addThingsPresenter;

    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaoSession();
        initPresenter();
        bindViewToPresenter();

        loadIntentExtras();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_things;
    }

    private void initDaoSession() {
        daoSession = ((NoteThings) getApplication()).getDaoSession();
    }

    private void initPresenter() {
        addThingsPresenter = new AddThingsPresenter(daoSession);
    }

    private void bindViewToPresenter() {
        addThingsPresenter.setView(this);
    }

    private void loadIntentExtras() {
        Intent intent = getIntent();
        if (null != intent && intent.hasExtra(HomeActivity.EXTRA_IS_ADD_NEW)) {
            boolean isAdd = intent.getBooleanExtra(HomeActivity.EXTRA_IS_ADD_NEW, false);
            if (!isAdd) {
                if (intent.hasExtra(HomeActivity.EXTRA_THING_ID)
                    && intent.hasExtra(HomeActivity.EXTRA_THING_NAME)
                    && intent.hasExtra(HomeActivity.EXTRA_THING_QUANTITY)
                    && intent.hasExtra(HomeActivity.EXTRA_THING_SUPPLIER)
                    && intent.hasExtra(HomeActivity.EXTRA_THING_DATE)){
                    setUpManageThings();
                }
            }
        }
    }

    private void setUpManageThings() {
        fabRemove.setVisibility(View.VISIBLE);
    }

    public void addThing (View view) {
        if (TextUtils.isEmpty(etNameOfThing.getText().toString())
            || TextUtils.isEmpty(spSupplier.getSelectedItem().toString())
            || TextUtils.isEmpty(etQuantity.getText().toString())
            || TextUtils.isEmpty(etDate.getText().toString())) {
            Toast.makeText(this, "Please fill in all data", Toast.LENGTH_LONG).show();
        } else {
            addThingsPresenter.addNewThing(
                etNameOfThing.getText().toString(), spSupplier.getSelectedItem().toString(),
                Long.valueOf(etQuantity.getText().toString()), etDate.getText().toString());
        }
    }

    public void deleteThing (View view) {

    }

    @Override
    public void showSuccessAddThing() {
        Toast.makeText(this, "Data sucessfully added", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
