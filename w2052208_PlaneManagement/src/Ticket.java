import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    // Fields to store ticket information
    private char row;
    private int seatNumber;
    private int price;
    private Person person;

    // Constructor to initialize ticket attributes
    public Ticket(char row, int seatNumber, int price, Person person) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
        this.person = person;
    }

    // Getters and setters for the fields
    public char getRow()
    {
        return row;
    }

    public void setRow(char row)
    {
        this.row = row;
    }

    public int getSeatNumber()
    {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber)
    {
        this.seatNumber = seatNumber;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void TicketInfo() {
        System.out.println("Ticket Information");
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: £" + price);
        System.out.println();
        System.out.println("Person information");
        person.personDetails();
    }


    public void save() {
        try {
            String save = getRow() + "" + getSeatNumber() + ".txt";
            FileWriter newfile = new FileWriter(save);

            newfile.write("Ticket Information\n");
            newfile.write("Row: " + getRow() + "\n");
            newfile.write("Seat: " + getSeatNumber() + "\n");
            newfile.write("Price: £" + getPrice() + "\n");
            newfile.write("**********************************************\n");
            newfile.write("\nPerson Info\n");
            newfile.write("Name: " + person.getName() + "\n");
            newfile.write("Surname: " + person.getSurname() + "\n");
            newfile.write("Email: " + person.getEmail() + "\n");
            newfile.close();
        } catch (IOException e) {
            System.out.println("Error!");
        }
    }
}

