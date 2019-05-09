package ch.makery.address.view;

import ch.makery.address.model.Person;
import java.text.DateFormatSymbols;
import java.util.List;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class BirthdayStatisticsController{
    
    @FXML
    private BarChart<String, Integer> barChart;
    
    @FXML
    private CategoryAxis xAxis;
    
    private ObservableList<String> monthNames = 
            FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        String[] months = DateFormatSymbols
                .getInstance(Locale.ENGLISH)
                .getMonths();
        monthNames.addAll(months);
        
        xAxis.setCategories(monthNames);
    }
    
    public void setPersonData(List<Person> persons) {
        int[] monthCounter = new int[12];
        persons.stream().forEach((Person person) -> {
            int month = person.getBirthday().getMonthValue()-1;
            monthCounter[month]++;
        });
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        for(int i = 0; i < monthCounter.length; i++){
            series.getData()
                    .add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        
        barChart.getData().add(series);
    }
    
    
}
