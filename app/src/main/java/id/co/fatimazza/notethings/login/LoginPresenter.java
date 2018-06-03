package id.co.fatimazza.notethings.login;


import id.co.fatimazza.notethings.base.BasePresenter;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.User;
import id.co.fatimazza.notethings.database.UserDao;

/**
 * Created by fatimazza on 6/3/18.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View>
    implements LoginContract.Presenter {

    public LoginPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void logInUser(String username, String password) {
        boolean isLogin = true;
        if(getDaoSession().getUserDao().queryBuilder()
                    .where(UserDao.Properties.Username.eq(username),
                        UserDao.Properties.Password.eq(password))
                    .list().size() == 0) {
            getView().logInStatus(!isLogin);
                } else {
            getView().logInStatus(isLogin);
        }
    }

    @Override
    public void registerUser(String username, String password) {
        boolean isRegistered = true;

        if(getDaoSession().getUserDao().queryBuilder()
            .where(UserDao.Properties.Username.eq(username),
                UserDao.Properties.Password.eq(password))
            .list().size() == 1) {
            getView().registerStatus(isRegistered);
        } else {
            getDaoSession().getUserDao().insert(
                new User(null, username, password, "04/06/2018"));
            getView().registerStatus(!isRegistered);
        }
    }
}
