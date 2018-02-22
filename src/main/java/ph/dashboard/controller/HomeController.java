package ph.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ph.dashboard.models.Person;
import ph.dashboard.service.PersonService;
import ph.dashboard.widget.Alert.AlertPriority;
import ph.dashboard.widget.containers.AlertDataContainer;
import ph.dashboard.widget.containers.LineChartDataContainer;
import ph.dashboard.widget.Widget;
import ph.dashboard.widget.containers.RoundChartDataContainer;
import ph.dashboard.widget.containers.TableDataContainer;
import ph.dashboard.widget.size.SizeClass;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leon on 23/01/2017.
 */
@Controller
public class HomeController {

    private final PersonService personService;

    private final Widget complexTable;

    private final Widget table;

    private final Widget lineChart;

    private final Widget barChart;

    private final Widget donutChart;

    private final Widget pieChart;

    private final Widget alert;

    @Autowired
    public HomeController(PersonService personService,
                          @Qualifier("complexTableWidget") Widget complexTable,
                          @Qualifier("tableWidget") Widget table,
                          @Qualifier("lineChartWidget") Widget lineChart,
                          @Qualifier("barChartWidget") Widget barChart,
                          @Qualifier("donutChartWidget") Widget donutChart,
                          @Qualifier("pieChartWidget") Widget pieChart,
                          @Qualifier("alertWidget") Widget alert) {
        this.personService = personService;
        this.complexTable = complexTable;
        this.table = table;
        this.lineChart = lineChart;
        this.barChart = barChart;
        this.donutChart = donutChart;
        this.pieChart = pieChart;
        this.alert = alert;
    }


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

        /*Alerts*/
        AlertDataContainer alertDataContainer = new AlertDataContainer("12", AlertPriority.LOW,"New Customers");
        modelAndView.addObject("alertLow", alert.create(alertDataContainer));
        alertDataContainer = new AlertDataContainer("4", AlertPriority.MEDIUM,"404 Eror");
        modelAndView.addObject("alertMedium", alert.create(alertDataContainer));
        alertDataContainer = new AlertDataContainer("5%", AlertPriority.HIGH,"Error Rate");
        modelAndView.addObject("alertHigh", alert.create(alertDataContainer));
        alertDataContainer = new AlertDataContainer("18%", AlertPriority.VERY_HIGH,"Sales Rate");
        modelAndView.addObject("alertVeryHigh", alert.create(alertDataContainer));

        modelAndView.setViewName("content/testContent");
        return modelAndView;
    }

    private Map<String,Double> getChartInfo() {
        return personService.getChartInfo();
    }


}
