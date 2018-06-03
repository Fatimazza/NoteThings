package id.co.fatimazza.notethings.managethings;

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

import java.util.List;

import butterknife.BindView;
import id.co.fatimazza.notethings.NoteThings;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.base.BaseActivity;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.Things;
import id.co.fatimazza.notethings.home.HomeActivity;

public class ManageThingsActivity extends BaseActivity implements ManageThingsContract.View {

    @BindView(R.id.et_nameof_thing)
    public EditText etNameOfThing;

    @BindView(R.id.sp_supplier)
    public Spinner spSupplier;

    @BindView(R.id.et_quantity)
    public EditText etQuantity;

    @BindView(R.id.et_date)
    public EditText etDate;

    @BindView(R.id.fab_add)
    public FloatingActionButton fabAdd;

    @BindView(R.id.fab_edit)
    public FloatingActionButton fabEdit;

    @BindView(R.id.fab_remove)
    public FloatingActionButton fabRemove;

    private ManageThingsPresenter manageThingsPresenter;

    private DaoSession daoSession;

    private long idThing = -1;

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
        manageThingsPresenter = new ManageThingsPresenter(daoSession);
    }

    private void bindViewToPresenter() {
        manageThingsPresenter.setView(this);
    }

    private void loadIntentExtras() {
        Intent intent = getIntent();
        if (null != intent && intent.hasExtra(HomeActivity.EXTRA_IS_ADD_NEW)) {
            boolean isAdd = intent.getBooleanExtra(HomeActivity.EXTRA_IS_ADD_NEW, false);
            if (!isAdd) {
                if (intent.hasExtra(HomeActivity.EXTRA_THING_ID)){
                    idThing = intent.getLongExtra(HomeActivity.EXTRA_THING_ID, -1);
                    setUpManageThings(idThing);
                }
            }
        }
    }

    private void setUpManageThings(long id) {
        fabAdd.setVisibility(View.GONE);
        fabEdit.setVisibility(View.VISIBLE);
        fabRemove.setVisibility(View.VISIBLE);
        manageThingsPresenter.loadDataThing(id);
    }

    public void addThing (View view) {
        if (TextUtils.isEmpty(etNameOfThing.getText().toString())
            || TextUtils.isEmpty(spSupplier.getSelectedItem().toString())
            || TextUtils.isEmpty(etQuantity.getText().toString())
            || TextUtils.isEmpty(etDate.getText().toString())) {
            Toast.makeText(this, "Please fill in all data", Toast.LENGTH_LONG).show();
        } else {
            manageThingsPresenter.addNewThing(
                etNameOfThing.getText().toString(), spSupplier.getSelectedItem().toString(),
                Long.valueOf(etQuantity.getText().toString()), etDate.getText().toString());
        }
    }

    public void editThing (View view) {
        if (TextUtils.isEmpty(etNameOfThing.getText().toString())
            || TextUtils.isEmpty(spSupplier.getSelectedItem().toString())
            || TextUtils.isEmpty(etQuantity.getText().toString())
            || TextUtils.isEmpty(etDate.getText().toString())) {
            Toast.makeText(this, "Please fill in all data", Toast.LENGTH_LONG).show();
        } else {
            manageThingsPresenter.editThing(
                idThing,
                etNameOfThing.getText().toString(),
                etQuantity.getText().toString(),
                spSupplier.getSelectedItem().toString(),
                etDate.getText().toString());
        }
    }

    public void deleteThing (View view) {
        if (idThing >= 0) {
            manageThingsPresenter.deleteThing(idThing);
        }
    }

    @Override
    public void showSuccessAddThing() {
        Toast.makeText(this, "Data sucessfully added", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showSuccessDeleteThing() {
        Toast.makeText(this, "Data sucessfully deleted", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void showSuccessLoadDataThing(List<Things> listOfThingById) {
        etNameOfThing.setText(listOfThingById.get(0).getName());
        etQuantity.setText(listOfThingById.get(0).getQuantity());
        etDate.setText(listOfThingById.get(0).getDate());

        for(int i= 0; i < spSupplier.getAdapter().getCount(); i++) {
            if(spSupplier.getAdapter().getItem(i).toString()
                .contains(listOfThingById.get(0).getSupplier())) {
                spSupplier.setSelection(i);
                break;
            }
        }
    }

    @Override
    public void showSuccessEditThing() {
        Toast.makeText(this, "Data sucessfully edited", Toast.LENGTH_LONG).show();
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
