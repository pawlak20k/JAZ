package pl.pjatk.pawkle.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.gson.Gson;
import pl.pjatk.pawkle.model.SearchData;
import pl.pjatk.pawkle.model.Nbp;
import pl.pjatk.pawkle.model.Details;
import pl.pjatk.pawkle.model.Rate;


@org.springframework.stereotype.Service
public class Service {

    public Nbp getActualRate(SearchData apiSearchData) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("http://api.nbp.pl/api/exchangerates/rates/a/")
                    .append(apiSearchData.getCode())
                    .append("/")
                    .append(dateFormatter(apiSearchData.getStartDate()))
                    .append("/")
                    .append(dateFormatter(apiSearchData.getEndDate()))
                    .append("/");
            HttpURLConnection connection = (HttpURLConnection) new URL(sb.toString()).openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("GET");
            String jsonString = readStream(connection.getInputStream());

            Gson gson = new Gson();

            Nbp currencyResponse = gson.fromJson(jsonString, Nbp.class);
            return currencyResponse;
        } catch (Exception e) {
            return new Nbp();
        }
    }

    private String dateFormatter(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    public Details calculateCurrencyDetails(Nbp nbp){
        if(nbp.getRates() == null)
            return new Details();
        Details details = new Details();
        double tempValue = 0;
        double avgValue;
        int counter = 0;
        for(Rate rate : nbp.getRates()){
            tempValue += rate.getMid();
            counter ++;
        }
        avgValue = tempValue/counter;
        details.setAvgCurrency(avgValue);
        details.setDeviation(Math.sqrt(calculateVariance(nbp.getRates(), counter, avgValue)));
        return details;
    }

    public List<Nbp> findCurrency(String code, String startDate, String endDate){

        SearchData searchData = new SearchData();
        List<Nbp> nbpList = new ArrayList<>();
        searchData.setCode(code);
        searchData.setStartDate(stringToDateFormatter(startDate));
        searchData.setEndDate(stringToDateFormatter(endDate));
        nbpList.add(getActualRate(searchData));

        return nbpList;
    }

    private double calculateVariance(List<Rate> rates, int counter, double avgValue){
        double tempValue = 0;
        for(Rate rate : rates){
            tempValue += Math.pow(rate.getMid() - avgValue, 2);
        }
        return tempValue/counter;
    }

    private Date stringToDateFormatter(String dateString){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
