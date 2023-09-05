package com.workintech.s17g2.model;

import org.springframework.stereotype.Component;



@Component
public class JuniorDeveloper extends Developer{
    public JuniorDeveloper() {
    }

    public JuniorDeveloper(long id, String name, double salary, Experience experience) {
        super(id, name, salary, experience);
    }

    public JuniorDeveloper(String name, double salary, Experience experience) {
        super(name, salary, experience);
    }

}
