package id.co.fatimazza.notethings.addthings;


import id.co.fatimazza.notethings.base.BasePresenter;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.Things;

/**
 * Created by fatimazza on 6/2/18.
 */

public class AddThingsPresenter extends BasePresenter<AddThingsContract.View>
    implements AddThingsContract.Presenter {

    public AddThingsPresenter(DaoSession daoSession) {
        super(daoSession);
    }

    @Override
    public void addNewThing(String name, String supplier, long quantity, String date) {
        getDaoSession().getThingsDao().insert(new Things(null, name, supplier, String.valueOf(quantity), date));
        getView().showSuccessAddThing();
    }
}
