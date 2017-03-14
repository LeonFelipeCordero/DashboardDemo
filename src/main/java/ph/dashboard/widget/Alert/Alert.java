package ph.dashboard.widget.Alert;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import ph.dashboard.widget.Widget;
import ph.dashboard.widget.containers.AlertDataContainer;
import ph.dashboard.widget.containers.DataContainer;

/**
 * Created by leon on 13/03/2017.
 */
@Component("alertWidget")
public class Alert extends Widget {
    @Override
    public String create(DataContainer data, String[] heads) {
        return null;
    }

    @Override
    public String create(DataContainer data) {
        final Context context = new Context();
        SpringTemplateEngine templateEngine = (SpringTemplateEngine) appContext.getBean(SpringTemplateEngine.class);
        AlertDataContainer alertDataContainer = (AlertDataContainer) data;
        context.setVariable("value", alertDataContainer.getValue());
        context.setVariable("priority", alertDataContainer.getPriority());
        context.setVariable("title",alertDataContainer.getTitle());
        return templateEngine.process(getViewName(), context);
    }

    @Override
    protected String getViewName() {
        return "widgets/alert";
    }
}
