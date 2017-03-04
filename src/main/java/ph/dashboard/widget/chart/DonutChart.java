package ph.dashboard.widget.chart;

import org.springframework.stereotype.Component;
import ph.dashboard.widget.containers.DataContainer;

/**
 * Created by leon on 01/03/2017.
 */
@Component("donutChartWidget")
public class DonutChart extends Chart {
    @Override
    public String create(DataContainer data, String[] heads) {
        return null;
    }

    @Override
    public String create(DataContainer data) {
        return super.create(data);
    }

    @Override
    protected String getViewName() {
        return "widgets/donutChart";
    }
}
