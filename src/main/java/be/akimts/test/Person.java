package be.akimts.test;


public class Person {

    private String nom;

    public Person(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Demo(
            value = {Person.class},
            alias = "alias",
            enumeration = Demo.DemoEnum.VALEUR2
    )
    public void saluer(){
        System.out.println("salut");
    }

    @Override
    public String toString() {
        return "Person(nom: "+this.nom+")";
    }


    
}
