package ph.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ph.dashboard.models.Person;
import ph.dashboard.service.PersonService;
import ph.dashboard.widget.containers.LineChartDataContainer;
import ph.dashboard.widget.Widget;
import ph.dashboard.widget.containers.RoundChartDataContainer;
import ph.dashboard.widget.containers.TableDataContainer;
import ph.dashboard.widget.size.SizeClass;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by leon on 23/01/2017.
 */
@Controller
public class HomeController {

    @Autowired
    private PersonService personService;

    @Resource(name = "complexTableWidget")
    private Widget complexTable;

    @Resource(name = "tableWidget")
    private Widget table;

    @Resource(name = "lineChartWidget")
    private Widget lineChart;

    @Resource(name = "barChartWidget")
    private Widget barChart;

    @Resource(name = "donutChartWidget")
    private Widget donutChart;

    @Resource(name = "pieChartWidget")
    private Widget pieChart;

    @RequestMapping(value = "/")
    public ModelAndView index(ModelAndView modelAndView) {

        List<Person> people = personService.getPersons();
        String[] header = {"name", "address", "age", "email", "sex"};

        /*TABLES*/
        /*COMPLEX TABLE*/
        TableDataContainer<Person> tableDataContainer = new TableDataContainer<>(Person.class, people, "Person Table", SizeClass.BIG_SIZE);
        modelAndView.addObject("complexTable", complexTable.create(tableDataContainer, header));
        /*SIMPLE TABLES*/
        tableDataContainer.setSize(SizeClass.MIDDLE_SIZE);
        modelAndView.addObject("simpleTable", table.create(tableDataContainer, header));
        /*SIMPLE TABLES*/
        modelAndView.addObject("simpleTable2", table.create(tableDataContainer, header));

        /*CHARTS*/
        /*LINE AND BAR CHART*/
        LineChartDataContainer lineChartDataContainer = new LineChartDataContainer(getChartInfo(), "Line Chart", SizeClass.MIDDLE_SIZE);
        lineChartDataContainer.setColor("#378ddd");
        modelAndView.addObject("lineChart", lineChart.create(lineChartDataContainer));

//        lineChartDataContainer.setTitle("Line Chart 2");
//        lineChartDataContainer.setColor("#c51d1d");
//        lineChartDataContainer.setCharData(getChartInfo());
//        modelAndView.addObject("lineChart2", lineChart.create(lineChartDataContainer));

        LineChartDataContainer barChartDataContainer = new LineChartDataContainer(getChartInfo(), "Bar Chart", SizeClass.MIDDLE_SIZE);
        barChartDataContainer.setColor("#c51d1d");
        modelAndView.addObject("barChart", barChart.create(barChartDataContainer));

//        /*DONUT CHART*/
        RoundChartDataContainer roundChartDataContainer = new RoundChartDataContainer(getChartInfo(), "Donut Chart", SizeClass.MIDDLE_SIZE);
        modelAndView.addObject("donutChart", donutChart.create(roundChartDataContainer));

//        roundChartDataContainer.setTitle("Donut Chart 2");
//        roundChartDataContainer.setCharData(getChartInfo());
//        modelAndView.addObject("donutChart2", donutChart.create(roundChartDataContainer));

        /*PIE CHART*/
        roundChartDataContainer.setTitle("Pie Chart");
        roundChartDataContainer.setCharData(getChartInfo());
        modelAndView.addObject("pieChart", pieChart.create(roundChartDataContainer));

//        roundChartDataContainer.setTitle("Pie Char 2");
//        roundChartDataContainer.setCharData(getChartInfo());
//        modelAndView.addObject("pieChart2", pieChart.create(roundChartDataContainer));

        modelAndView.setViewName("content/testContent");
        return modelAndView;
    }

    private Map<String,Double> getChartInfo() {
        return personService.getChartInfo();
    }


}
