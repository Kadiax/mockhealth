package lifeprotect.mock.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "room")
public class Room extends PersistableElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name="num")
    private String num;

    public Room() {}

    public Room(String num, List<Light> lights, List<Shutter> shutters, List<Heater> heaters, List<Clock> clocks) {
        this.num = num;

    }

    public String getNum() { return num; }

    public void setNum(String num) {
        this.num = num;
    }
}
