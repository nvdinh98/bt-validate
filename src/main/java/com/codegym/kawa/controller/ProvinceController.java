package com.codegym.kawa.controller;

import com.codegym.kawa.model.Customer;
import com.codegym.kawa.model.Provinces;
import com.codegym.kawa.service.customer.ICustomerService;
import com.codegym.kawa.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/provinces")
    public ModelAndView listProvinces() {
        Iterable<Provinces> provinces = provinceService.findAll();
        ModelAndView modelAndView =new ModelAndView("/province/list");
        modelAndView.addObject("provinces",provinces);
        return modelAndView;
    }
    @GetMapping("/create-province")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Provinces());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView saveProvince(@ModelAttribute("province") Provinces provinces){
        provinceService.save(provinces);
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Provinces());
        modelAndView.addObject("message","da tao thanh cong");
        return modelAndView;
    }

    @GetMapping("/edit-provinces/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Provinces> provinces = provinceService.findById(id);
        if (provinces.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/province/edit");
            modelAndView.addObject("province",provinces.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }
    @PostMapping("/edit-province")
    public ModelAndView updateProvince(@ModelAttribute("province") Provinces provinces){
        provinceService.save(provinces);
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        modelAndView.addObject("province", provinces);
        modelAndView.addObject("message","da sua thanh cong");
        return modelAndView;
    }
    @GetMapping("/delete-provinces/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Provinces> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/delete");
            modelAndView.addObject("provinces", province.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-province")
    public String deleteProvince(@ModelAttribute("provinces") Provinces provinces) {
        provinceService.remove(provinces.getId());
        return "redirect:provinces";
    }
    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id){
        Optional<Provinces> provincesOptional = provinceService.findById(id);
        if (!provincesOptional.isPresent()){
            return new ModelAndView("/error.404");
        }
        Iterable<Customer> customers =  customerService.findALlByProvince(provincesOptional.get());
        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province",provincesOptional.get());
        modelAndView.addObject("customers",customers);
        return modelAndView;
    }


}
