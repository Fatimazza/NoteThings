package id.co.fatimazza.notethings.home;

import id.co.fatimazza.notethings.base.BasePresenter;
import id.co.fatimazza.notethings.database.DaoSession;

/**
 * Created by fatimazza on 6/3/18.
 */

public class HomePresenter extends BasePresenter<HomeContract.View>
    implements HomeContract.Presenter{

    public HomePresenter(DaoSession daoSession) {
        super(daoSession);
    }
}
