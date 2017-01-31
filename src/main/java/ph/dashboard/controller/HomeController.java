package ph.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ph.dashboard.models.Person;
import ph.dashboard.service.PersonService;
import ph.dashboard.widget.CharDataContainer;
import ph.dashboard.widget.Widget;
import ph.dashboard.widget.TableDataContainer;
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

    @Resource(name = "chartWidget")
    private Widget chart;

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
        /*LINE CHART*/
        CharDataContainer charDataContainer = new CharDataContainer(getChartInfo(), "Line Chart", SizeClass.BIG_SIZE);
        modelAndView.addObject("lineChart", chart.create(charDataContainer));

        modelAndView.setViewName("content/testContent");
        return modelAndView;
    }

    private Map<String,Double> getChartInfo() {
        return personService.getChartInfo();
    }


}