package lifeprotect.mock.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

public abstract class PersistableElement implements Serializable {


    private Long id;


    protected Timestamp optlock;

    public PersistableElement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOptlock() {
        return optlock;
    }

    public void setOptlock(Timestamp optlock) {
        this.optlock = optlock;
    }
}
