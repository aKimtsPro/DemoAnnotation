package be.akimts.test.container;

@ToInstanciate("service-2")
public class Service2 {

    private final Service1 service1;

    public Service2(Service1 service1) {
        this.service1 = service1;
    }

    public void test(){
        System.out.println("test services 2");
    }

    public Service1 getService1() {
        return service1;
    }
}
