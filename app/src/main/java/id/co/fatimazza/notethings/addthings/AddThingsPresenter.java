package id.co.fatimazza.notethings.addthings;


import id.co.fatimazza.notethings.base.BasePresenter;

/**
 * Created by fatimazza on 6/2/18.
 */

public class AddThingsPresenter extends BasePresenter<AddThingsContract.View>
    implements AddThingsContract.Presenter {

    public AddThingsPresenter() {

    }

    @Override
    public void addNewThing(String name, String supplier, long quantity, String date) {
        getView().showSuccessAddThing();
    }
}
