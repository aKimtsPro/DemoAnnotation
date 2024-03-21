package be.akimts.test;

import be.akimts.test.container.Service1;
import be.akimts.test.container.Service2;
import be.akimts.test.container.ServiceContainer;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
            ServiceContainer.loadServices();
            Service1 service1 = ServiceContainer.getInstance(Service1.class);
            Service2 service2 = ServiceContainer.getInstance(Service2.class);
            service1.test();
            service2.test();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }
}
