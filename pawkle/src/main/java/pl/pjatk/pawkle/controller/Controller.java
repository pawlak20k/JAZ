package pl.pjatk.pawkle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.pjatk.pawkle.model.SearchData;
import pl.pjatk.pawkle.model.Nbp;
import pl.pjatk.pawkle.service.Service;

import javax.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Service service;


    @GetMapping(value = "/")
    public ModelAndView addFood() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("search_view", new SearchData());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @PostMapping(value = "/")
    public ModelAndView addFood(@Valid SearchData searchData) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("search_view", new SearchData());
        Nbp nbp = service.getActualRate(searchData);
        modelAndView.addObject("result_list", nbp);
        modelAndView.addObject("details_list", service.calculateCurrencyDetails(nbp));
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
