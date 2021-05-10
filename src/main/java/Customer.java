
import java.lang.*;
import java.util.*;

class Customer {
    private final String name;
    private final Vector<Rental> rentals = new Vector<Rental>();
    private int frequentRenterPoints = 0;
    private double totalAmount = 0;

    public Customer(String newName) {
        name = newName;
    }

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        Enumeration<Rental> enum_rentals = rentals.elements();

        return "Rental Record for " + this.getName() + "\n" + "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n" +
                calculateTotalAmount(enum_rentals) +
                //add footer lines
                "Amount owed is " + totalAmount + "\n" +
                "You earned " + frequentRenterPoints + " frequent renter points";
    }

    private String calculateTotalAmount(Enumeration<Rental> enum_rentals) {
        StringBuilder substring = new StringBuilder();
        while (enum_rentals.hasMoreElements()) {
            Rental each = enum_rentals.nextElement();
            //determine amounts for each line
            double thisAmount = each.amountFor();
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            substring.append("\t").append(each.getMovie().getTitle()).append("\t").append("\t").append(each.getDaysRented()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        return substring.toString();
    }



}
    