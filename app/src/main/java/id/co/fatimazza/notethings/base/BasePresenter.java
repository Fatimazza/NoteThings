package id.co.fatimazza.notethings.base;

/**
 * Created by fatimazza on 6/2/18.
 */

public class BasePresenter<T extends BaseContractView> {

    private T mvpView;

    public T getView() {
        return mvpView;
    }

    public void setView(T view) {
        this.mvpView = view;
    }

}
