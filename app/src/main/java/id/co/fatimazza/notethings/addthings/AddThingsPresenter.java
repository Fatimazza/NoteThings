package id.co.fatimazza.notethings.addthings;


import java.util.List;

import id.co.fatimazza.notethings.base.BasePresenter;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.Things;
import id.co.fatimazza.notethings.database.ThingsDao;

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

    @Override
    public void deleteThing(long id) {
        for (Things things : getDaoSession().getThingsDao().loadAll()) {
            if (id==things.getId()) {
                getDaoSession().getThingsDao().delete(things);
                getView().showSuccessDeleteThing();
                break;
            }
        }
    }

    @Override
    public void loadDataThing(long id) {
        for (Things things : getDaoSession().getThingsDao().loadAll()) {
            if (id==things.getId()) {
                List<Things> listThingById = getDaoSession().getThingsDao().queryBuilder().where(
                    ThingsDao.Properties.Id.in(id)
                ).list();
                getView().showSuccessLoadDataThing(listThingById);
                break;
            }
        }
    }

    @Override
    public void editThing(long id, String name, String quantity, String supplier, String date) {
        for (Things things : getDaoSession().getThingsDao().loadAll()) {
            if (id==things.getId()) {
                things.setName(name);
                things.setQuantity(quantity);
                things.setSupplier(supplier);
                things.setDate(date);
                getDaoSession().getThingsDao().update(things);
                getView().showSuccessEditThing();
                break;
            }
        }
    }
}
