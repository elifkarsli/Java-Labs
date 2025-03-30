package LAB2;
import java.util.ArrayList;


public class User {
    String name;
    ArrayList<Pet> adoptedPets;

    public User(String name){
        this.name = name;
        this.adoptedPets = new ArrayList<>();
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    // adoptedPets için setter metoduna gerek yok çünkü listeye ekleme metodu kullnaıyoruz
    public ArrayList<Pet> getAdoptedPets(){
        return adoptedPets;
    }

    public void adoptPet(Pet pet){
        adoptedPets.add(pet);
        pet.setOwner(this.name); // evcil hayvanın sahibini güncellemek için kullandık yazmasak hep null verecekti
        System.out.println(this.name + "adopted : " + pet.getName()); 
    }

    public void carePet(String petName){

    }

}
