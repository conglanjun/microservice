package com.example.springbootdemo.controller;


import com.example.springbootdemo.model.City;
import com.example.springbootdemo.model.Student;
import com.example.springbootdemo.service.ICityService;
import com.example.springbootdemo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller // for thymeleaf show info in web
@RestController
public class HelloController {

    private ICityService cityService;

    private IStudentService studentService;

    @Autowired
    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setCityService(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String index() {
        return "Greeting from spring boot!!!";
    }

    @GetMapping("/showCities")
    public String findCities(Model model) {
        var cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "showCities";
    }

    @PostMapping("/add/{name}/{population}")
    public City addCity(@PathVariable String name, @PathVariable int population) {
        var city = cityService.saveCity(new City(name, population));
        return city;
    }

    @GetMapping("/get/{id}")
    public City getCityById(@PathVariable Long id) {
        return cityService.getCityById(id);
    }

    @GetMapping("/get/student/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/add/student")
    public Student addStudent(@RequestBody Student student) {
        return studentService.insert(student);
    }
}
