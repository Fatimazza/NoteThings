package id.co.fatimazza.notethings.addthings;

import java.util.List;

import id.co.fatimazza.notethings.base.BaseContractView;
import id.co.fatimazza.notethings.database.Things;

/**
 * Created by fatimazza on 6/2/18.
 */

public interface AddThingsContract {

    interface View extends BaseContractView {

        void showSuccessAddThing();

        void showSuccessDeleteThing();

        void showSuccessLoadDataThing(List<Things> listOfThingById);

        void showSuccessEditThing();

    }

    interface Presenter {

        void addNewThing(String name, String supplier, long quantity, String date);

        void deleteThing(long id);

        void loadDataThing(long id);

        void editThing(long id, String name, String quantity, String supplier, String date);
    }

}
