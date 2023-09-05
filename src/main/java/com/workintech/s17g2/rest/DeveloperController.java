package com.workintech.s17g2.rest;

import com.workintech.s17g2.model.*;

import com.workintech.s17g2.tax.Taxable;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DeveloperController {

    private Map<Long, Developer> developers;

    private Taxable taxable;

    @RequestMapping("/developers")

    @PostConstruct
    public void init() {
        developers = new HashMap<>();
    }

    @Autowired
    public DeveloperController(@Qualifier("developerTax") Taxable taxable) {
        this.taxable = taxable;
    }

    @GetMapping("/")
    public List<Developer> getAll() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Developer getId(@PathVariable long id) {
        return developers.get(id);
    }

    @PostMapping("/")
    public Developer add(@RequestBody Developer developer) {
        Developer savedDeveloper;
        if (developer.getExperience().name().equalsIgnoreCase("junior")) {
            savedDeveloper = new JuniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getSimpleTaxRate()),
                    developer.getExperience());
        } else if (developer.getExperience().name().equalsIgnoreCase("mid")) {
            savedDeveloper = new MediumDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getMiddleTaxRate()),
                    developer.getExperience());
        } else if (developer.getExperience().name().equalsIgnoreCase("senior")) {
            savedDeveloper = new SeniorDeveloper(developer.getId(), developer.getName(),
                    developer.getSalary() - (developer.getSalary() * taxable.getUpperTaxRate()),
                    developer.getExperience());
        } else {
            savedDeveloper = null;
        }
        developers.put(savedDeveloper.getId(), savedDeveloper);
        return developers.get(savedDeveloper.getId());
    }
    @PutMapping("/{id}")
    public Developer update(@PathVariable long id, @RequestBody Developer developer) {
        Developer existingDeveloper = developers.get(id);
        if (existingDeveloper == null) {
            return null;
        }
        existingDeveloper.setName(developer.getName());
        existingDeveloper.setSalary(developer.getSalary());
        existingDeveloper.setExperience(developer.getExperience());

        if (existingDeveloper.getExperience() == Experience.JUNIOR) {
            existingDeveloper.setSalary(existingDeveloper.getSalary() - (existingDeveloper.getSalary() * taxable.getSimpleTaxRate()));
        } else if (existingDeveloper.getExperience() == Experience.MID) {
            existingDeveloper.setSalary(existingDeveloper.getSalary() - (existingDeveloper.getSalary() * taxable.getMiddleTaxRate()));
        } else if (existingDeveloper.getExperience() == Experience.SENIOR) {
            existingDeveloper.setSalary(existingDeveloper.getSalary() - (existingDeveloper.getSalary() * taxable.getUpperTaxRate()));
        }
        return existingDeveloper;
    }
    @DeleteMapping("/{id}")
    public Developer delete(@PathVariable long id) {
        Developer removedDeveloper = developers.remove(id);
        return removedDeveloper;
    }

}

