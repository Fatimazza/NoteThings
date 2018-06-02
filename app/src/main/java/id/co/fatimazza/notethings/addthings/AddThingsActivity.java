package id.co.fatimazza.notethings.addthings;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import id.co.fatimazza.notethings.R;

public class AddThingsActivity extends AppCompatActivity implements AddThingsContract.View {

    private AddThingsPresenter addThingsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_things);

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initPresenter();
        bindViewToPresenter();
    }

    private void initPresenter() {
        addThingsPresenter = new AddThingsPresenter();
    }

    private void bindViewToPresenter() {
        addThingsPresenter.setView(this);
    }

    public void addThing (View view) {
        addThingsPresenter.addNewThing();
    }

    @Override
    public void showLog() {
        Toast.makeText(this, "This is MVP Sample", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
