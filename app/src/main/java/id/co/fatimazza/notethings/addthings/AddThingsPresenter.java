package id.co.fatimazza.notethings.addthings;

import android.content.Context;

import id.co.fatimazza.notethings.base.BasePresenter;

/**
 * Created by fatimazza on 6/2/18.
 */

public class AddThingsPresenter extends BasePresenter<AddThingsContract.View>
    implements AddThingsContract.Presenter {

    public AddThingsPresenter() {

    }

    @Override
    public void addNewThing() {
        getView().showLog();
    }
}
