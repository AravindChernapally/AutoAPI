package com.lds.models.api;

import lombok.Data;

@Data
public class WeatherForecast {

    public String date;
    public Integer tempratureC;
    public String summary;
    public Integer tempratureF;

}
