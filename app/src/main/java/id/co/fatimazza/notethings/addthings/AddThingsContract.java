package id.co.fatimazza.notethings.addthings;

import id.co.fatimazza.notethings.base.BaseContractView;

/**
 * Created by fatimazza on 6/2/18.
 */

public interface AddThingsContract {

    interface View extends BaseContractView {

        void showSuccessAddThing();

    }

    interface Presenter {

        void addNewThing(String name, String supplier, long quantity, String date);

    }

}
