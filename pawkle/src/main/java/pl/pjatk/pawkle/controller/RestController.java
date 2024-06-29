package pl.pjatk.pawkle.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.pjatk.pawkle.model.Nbp;
import pl.pjatk.pawkle.service.Service;

import java.lang.reflect.Type;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@Tag(name = "Currency", description = "Endpoints for checking currency")
public class RestController {

    @Autowired
    Service service;

    Gson gson = new Gson();

    @GetMapping(value = "/find/{code}/{startDate}/{endDate}")
    @Operation(summary = "Get currency exchange rate", description = "Returns a currency exchange rate by currency code and date")
    public String checkCurrency(@PathVariable String code
            , @PathVariable  String startDate
            , @PathVariable  String endDate){

        Type nbpListType = new TypeToken<List<Nbp>>() {
        }.getType();
        return gson.toJson(service.findCurrency(code, startDate, endDate), nbpListType);
    }
}

//przykładowy GET: http://localhost:8080/find/chf/2022-06-28/2022-06-29
//sprawdzenie kursu franka szajcarskiego^