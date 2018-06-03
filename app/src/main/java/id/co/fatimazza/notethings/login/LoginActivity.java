package id.co.fatimazza.notethings.login;

import android.support.v4.app.NavUtils;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import id.co.fatimazza.notethings.R;
import id.co.fatimazza.notethings.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    @BindView(R.id.et_password)
    TextView etPassword;

    @BindView(R.id.et_confirm)
    TextView etConfirmPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_register)
    Button btnRegister;

    boolean isRegistered = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }


    private void initView() {
    }

    @OnClick(R.id.tv_login_register)
    public void setLoginOrRegister(View view) {
        isRegistered = !isRegistered;
        etConfirmPassword.setVisibility(isRegistered ? View.GONE : View.VISIBLE);
        btnRegister.setVisibility(isRegistered ? View.GONE : View.VISIBLE);
        btnLogin.setVisibility(isRegistered ? View.VISIBLE : View.GONE);
        tvLoginRegister.setText(isRegistered ? "Belum punya akun?" : "Sudah punya akun?");
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
