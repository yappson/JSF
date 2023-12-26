package com.emp.manage.Charts;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.emp.manage.Repo.EmployeRepository;

@Component
@ManagedBean
public class ChartBean {

    @Autowired
    private EmployeRepository employeRepository;

    private int[] values;
    private String[] labels;
    private String[] colors;

    @PostConstruct
    public void init() {
        Long HRC = employeRepository.getCountByDepartment("hr");
        Long ITC = employeRepository.getCountByDepartment("IT");
        Long managC = employeRepository.getCountByDepartment("Management");

        values = new int[]{HRC.intValue(), ITC.intValue(), managC.intValue()};
        labels = new String[]{"HR", "IT", "Management"};
        colors = new String[]{"#FF6384", "#36A2EB", "#FFCE56"};
    }

    public String getChartData() {
        Long HRC = employeRepository.getCountByDepartment("hr");
        Long ITC = employeRepository.getCountByDepartment("IT");
        Long managC = employeRepository.getCountByDepartment("Management");

        values = new int[]{HRC.intValue(), ITC.intValue(), managC.intValue()};
        labels = new String[]{"HR", "IT", "Management"};
        colors = new String[]{"#FF6384", "#36A2EB", "#FFCE56"};

        // Convert data to JSON format
        StringBuilder jsonData = new StringBuilder("{");
        jsonData.append("\"labels\":").append(toJsonArray(labels)).append(",");
        jsonData.append("\"values\":").append(toJsonArray(values)).append(",");
        jsonData.append("\"colors\":").append(toJsonArray(colors));
        jsonData.append("}");

        return jsonData.toString();
    }

    private String toJsonArray(int[] array) {
        if (array == null) {
            return "[]";
        }

        StringBuilder jsonArray = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            jsonArray.append(array[i]);
            if (i < array.length - 1) {
                jsonArray.append(",");
            }
        }
        jsonArray.append("]");
        return jsonArray.toString();
    }

    private String toJsonArray(String[] array) {
        if (array == null) {
            return "[]";
        }

        StringBuilder jsonArray = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            jsonArray.append("\"").append(array[i]).append("\"");
            if (i < array.length - 1) {
                jsonArray.append(",");
            }
        }
        jsonArray.append("]");
        return jsonArray.toString();
    }
}
