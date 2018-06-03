package id.co.fatimazza.notethings.base;

import id.co.fatimazza.notethings.database.DaoSession;

/**
 * Created by fatimazza on 6/2/18.
 */

public class BasePresenter<T extends BaseContractView> {

    DaoSession daoSession;

    private T mvpView;

    public BasePresenter(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public T getView() {
        return mvpView;
    }

    public void setView(T view) {
        this.mvpView = view;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
