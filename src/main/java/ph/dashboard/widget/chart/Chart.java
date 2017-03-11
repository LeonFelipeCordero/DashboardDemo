package ph.dashboard.widget.chart;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import ph.dashboard.widget.containers.DataContainer;
import ph.dashboard.widget.Widget;
import ph.dashboard.widget.containers.LineChartDataContainer;

/**
 * Created by leon on 01/03/2017.
 */
public class Chart extends Widget {

    @Override
    public String create(DataContainer data, String[] heads) {
        return null;
    }

    @Override
    public String create(DataContainer data) {
        final Context context = new Context();
        SpringTemplateEngine templateEngine = (SpringTemplateEngine) appContext.getBean(SpringTemplateEngine.class);
        context.setVariable("data", data.getCharData());
        context.setVariable("title", data.getTitle());
        context.setVariable("id", data.getTitle().trim().replace(" ", ""));
        context.setVariable("size", data.getSize());
        setColor(context, data);
        return templateEngine.process(getViewName(), context);
    }

    protected void setColor(Context context, DataContainer data) {
        LineChartDataContainer lineChartDataContainer = (LineChartDataContainer) data;
        if(lineChartDataContainer.getColor() != null) {
            context.setVariable("color", lineChartDataContainer.getColor());
        }else{
            context.setVariable("color", "#378ddd");
        }
    }

    @Override
    protected String getViewName() {
        return null;
    }
}
