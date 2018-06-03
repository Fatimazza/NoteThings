package id.co.fatimazza.notethings.login;

import id.co.fatimazza.notethings.base.BasePresenter;
import id.co.fatimazza.notethings.database.DaoSession;

/**
 * Created by fatimazza on 6/3/18.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
    implements LoginContract.Presenter {

    public LoginPresenter(DaoSession daoSession) {
        super(daoSession);
    }
}
