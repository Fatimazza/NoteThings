package id.co.fatimazza.notethings.addthings;

import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.BindView;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.base.BaseActivity;

public class AddThingsActivity extends BaseActivity implements AddThingsContract.View {

    @BindView(R.id.et_nameof_thing)
    public EditText etNameOfThing;

    @BindView(R.id.sp_supplier)
    public Spinner spSupplier;

    @BindView(R.id.et_quantity)
    public EditText etQuantity;

    @BindView(R.id.et_date)
    public EditText etDate;

    private AddThingsPresenter addThingsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();
        bindViewToPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_add_things;
    }

    private void initPresenter() {
        addThingsPresenter = new AddThingsPresenter();
    }

    private void bindViewToPresenter() {
        addThingsPresenter.setView(this);
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
