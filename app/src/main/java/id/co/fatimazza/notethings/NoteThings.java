package id.co.fatimazza.notethings;

import org.greenrobot.greendao.AbstractDaoSession;

import android.app.Application;

import id.co.fatimazza.notethings.database.DaoMaster;
import id.co.fatimazza.notethings.database.DaoSession;

/**
 * Created by fatimazza on 6/3/18.
 */

public class NoteThings extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        daoSession = new DaoMaster(
            new DaoMaster.DevOpenHelper(this, "note_things.db").getWritableDatabase()).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
