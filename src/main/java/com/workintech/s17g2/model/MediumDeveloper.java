package com.workintech.s17g2.model;

import org.springframework.stereotype.Component;



@Component
public class MediumDeveloper extends Developer{
    public MediumDeveloper() {
    }

    public MediumDeveloper(long id, String name, double salary, Experience experience) {
        super(id, name, salary, experience);
    }

    public MediumDeveloper(String name, double salary, Experience experience) {
        super(name, salary, experience);
    }


}
