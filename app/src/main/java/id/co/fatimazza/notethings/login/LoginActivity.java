package id.co.fatimazza.notethings.login;

import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import id.co.fatimazza.notethings.NoteThings;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.base.BaseActivity;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.home.HomeActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_confirm)
    EditText etConfirmPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    private LoginPresenter loginPresenter;

    private DaoSession daoSession;

    boolean isRegistered = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDaoSession();
        initPresenter();
        bindViewToPresenter();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    private void initDaoSession() {
        daoSession = ((NoteThings) getApplication()).getDaoSession();
    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter(daoSession);
    }

    private void bindViewToPresenter() {
        loginPresenter.setView(this);
    }

    @OnClick(R.id.tv_login_register)
    public void setLoginOrRegister(View view) {
        isRegistered = !isRegistered;
        etConfirmPassword.setVisibility(isRegistered ? View.GONE : View.VISIBLE);
        btnRegister.setVisibility(isRegistered ? View.GONE : View.VISIBLE);
        btnLogin.setVisibility(isRegistered ? View.VISIBLE : View.GONE);
        tvLoginRegister.setText(isRegistered ? "Not Yet Registered?" : "Already Registered?");
    }

    public void doLogin (View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)
            || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
        } else {
            loginPresenter.logInUser(username, password);
        }
    }

    public void doRegister (View view) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(username)
            || TextUtils.isEmpty(password)
            || TextUtils.isEmpty(confirm)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show();
        } else if (!etConfirmPassword.getText().toString()
            .equals(etPassword.getText().toString())) {
            Toast.makeText(this, "Password and Confirmation do not match", Toast.LENGTH_LONG).show();
        } else {
            loginPresenter.registerUser(username, password);
        }
    }

    @Override
    public void logInStatus(boolean status) {
        if (status) {
            Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
            HomeActivity.isLogin = true;
            finish();
        } else {
            Toast.makeText(this, "Username or Password do not match", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void registerStatus(boolean isRegistered) {
        if (isRegistered) {
            Toast.makeText(this, "Failed to register, Already exist", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Registration Success", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void showMaxRegistration() {
        Toast.makeText(this, "Failed. Max 5 Users already registered.", Toast.LENGTH_LONG).show();
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
