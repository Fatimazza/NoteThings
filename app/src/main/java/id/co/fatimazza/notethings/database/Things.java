package id.co.fatimazza.notethings.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fatimazza on 6/3/18.
 */

@Entity(nameInDb = "things")
public class Things {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "supplier")
    private String supplier;

    @Property(nameInDb = "quantity")
    private String quantity;

    @Property(nameInDb = "date")
    private String date;

    @Generated(hash = 713350473)
    public Things(Long id, String name, String supplier, String quantity,
            String date) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        this.date = date;
    }

    @Generated(hash = 1715286268)
    public Things() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
