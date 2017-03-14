package ph.dashboard.widget.containers;

/**
 * Created by leon on 13/03/2017.
 */
public class AlertDataContainer extends DataContainer {

    private String priority;
    private String value;
    private String pic;

    public AlertDataContainer(String value, String priority, String title) {
        this.priority = priority;
        this.value = value;
        this.title = title;
    }

    public AlertDataContainer() {
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
