public class Person {

    // Attributes to store person's name, surname, and email
    private String name;
    private String surname;
    private String email;

    // Constructor to initialize person attributes
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getter and setter methods for name, surname, and email attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //print info method
    public void personDetails(){
        System.out.println("Name : " + getName());
        System.out.println("Surname :" + getSurname());
        System.out.println("Email :" + getEmail());
        System.out.println();
    }



}

