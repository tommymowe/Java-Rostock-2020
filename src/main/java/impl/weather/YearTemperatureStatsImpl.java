package impl.weather;

import api.weather.DayTemperatureInfo;
import api.weather.YearTemperatureStats;

import java.time.Month;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static java.time.Month.*;

public class YearTemperatureStatsImpl implements YearTemperatureStats {
    private Map<Month, Map<Integer, DayTemperatureInfoImpl>> weatherMap;

    @Override
    public void updateStats(DayTemperatureInfoImpl info) {
        int day = info.getDay();
        Month month = info.getMonth();

        if (weatherMap.containsKey(month)) {
            Map<Integer, DayTemperatureInfoImpl> mapMonth = weatherMap.get(month);
            mapMonth.put(day, info);
            weatherMap.put(month, mapMonth);
        }
    }

    @Override
    public Double getAverageTemperature(Month month) {
        if (weatherMap.containsKey(month) == true) {
            Map<Integer, DayTemperatureInfoImpl> mapMonth = weatherMap.get(month);
            int days = weatherMap.size();
            int sum = 0;

            Collection<DayTemperatureInfoImpl> elements = mapMonth.values();
            for (DayTemperatureInfoImpl day : elements) {
                sum = sum + day.getTemperature();
            }
            return (double) sum / days;
        }
        return null;
    }

    @Override
    public Map<Month, Integer> getMaxTemperature() {
        Collection<Map<Integer, DayTemperatureInfoImpl>> months = weatherMap.values();
        Map<Month, Integer> output = new HashMap<>();

        for(Map<Integer,DayTemperatureInfoImpl> month : months){
            Collection<DayTemperatureInfoImpl> days = month.values();
            Integer monthMaxTemperature = null;
            Month currentMonth = null;

            for(DayTemperatureInfoImpl day : days){
                int dayTemperature = day.getTemperature();
                currentMonth = day.getMonth();

                if(monthMaxTemperature == null || monthMaxTemperature < dayTemperature){
                    monthMaxTemperature = dayTemperature;
                }
            }
            output.put(currentMonth, monthMaxTemperature);
        }

        return output;
    }

    @Override
    public List<DayTemperatureInfoImpl> getSortedTemperature(Month month) {
        if (weatherMap.containsKey(month) == true) {
            Map<Integer,DayTemperatureInfoImpl> mapMonth = weatherMap.get(month);
            Collection<DayTemperatureInfoImpl> days = mapMonth.values();
            Collection<Integer> temperature =
        }

        return null;
    }

    @Override
    public DayTemperatureInfoImpl getTemperature(int day, Month month) {

        if (weatherMap.containsKey(month) == true) {
            Map<Integer, DayTemperatureInfoImpl> mapMonth = weatherMap.get(month);

            if (mapMonth.containsKey(day) == true) {
                DayTemperatureInfoImpl output = mapMonth.get(day);
                return output;

            }
            return null;
        }
        return null;
    }
}
