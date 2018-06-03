package id.co.fatimazza.notethings.login;

import id.co.fatimazza.notethings.base.BaseContractView;

/**
 * Created by fatimazza on 6/3/18.
 */

public interface LoginContract {

    interface View extends BaseContractView {

        void logInStatus(boolean status);

        void registerStatus(boolean status);

        void showMaxRegistration();
    }

    interface Presenter {

        void logInUser(String username, String password);

        void registerUser(String username, String password);

    }
}

