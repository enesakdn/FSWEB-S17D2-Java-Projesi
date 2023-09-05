package com.workintech.s17g2.model;

import org.springframework.stereotype.Component;




@Component
public class SeniorDeveloper extends Developer{
    public SeniorDeveloper() {
    }

    public SeniorDeveloper(long id, String name, double salary, Experience experience) {
        super(id, name, salary, experience);
    }

    public SeniorDeveloper(String name, double salary, Experience experience) {
        super(name, salary, experience);
    }

}
