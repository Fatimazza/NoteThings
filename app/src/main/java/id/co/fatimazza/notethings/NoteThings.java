package id.co.fatimazza.notethings;


import android.app.Application;

import id.co.fatimazza.notethings.database.DaoMaster;
import id.co.fatimazza.notethings.database.DaoSession;
import id.co.fatimazza.notethings.database.Things;
import id.co.fatimazza.notethings.database.User;

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

        //add dummy data
        if(daoSession.getThingsDao().loadAll().size() == 0){
            daoSession.getThingsDao().insert(
                new Things(1L, "Teh Kotak Tapi Botol", "Pabrik Minuman", "3", "10/12/2018"));
        }
        if(daoSession.getUserDao().loadAll().size() == 0){
            daoSession.getUserDao().insert(
                new User(1L, "admin", "123456", "03/06/2018"));
        }
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
