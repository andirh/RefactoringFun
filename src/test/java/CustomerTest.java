import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerTest {

    private Customer cut;

    @Before
    public void setup(){
        cut = new Customer("customer");
    }

    @Test
    public void statementReturnsEmptyStatement(){
        String result = cut.statement();
        assertThat(result).contains("Rental Record for customer");
        assertThat(result).contains("\tTitle\t\tDays\tAmount");
        assertThat(result).contains("Amount owed is 0.0");
        assertThat(result).contains("You earned 0 frequent renter points");
    }

    @Test
    public void statementReturnsCorrectStatements(){
       Rental rental1 = mock(Rental.class);
       Rental rental2 = mock(Rental.class);
       Rental rental3 = mock(Rental.class);
       Movie movie1 = mock(Movie.class);
       Movie movie2 = mock(Movie.class);
       Movie movie3 = mock(Movie.class);

       when(rental1.getMovie()).thenReturn(movie1);
       when(rental2.getMovie()).thenReturn(movie2);
       when(rental3.getMovie()).thenReturn(movie3);

       when(rental1.getDaysRented()).thenReturn(42);
       when(rental2.getDaysRented()).thenReturn(2);
       when(rental3.getDaysRented()).thenReturn(1);

        when(movie1.getPriceCode()).thenReturn(0);
        when(movie2.getPriceCode()).thenReturn(1);
        when(movie3.getPriceCode()).thenReturn(2);

       when(movie1.getTitle()).thenReturn("title1");
       when(movie2.getTitle()).thenReturn("title2");
       when(movie3.getTitle()).thenReturn("title3");


        cut.addRental(rental1);
        cut.addRental(rental2);
        cut.addRental(rental3);

        String result = cut.statement();

        assertThat(result).contains("Rental Record for customer");
        assertThat(result).contains("\tTitle\t\tDays\tAmount");
        assertThat(result).contains("\ttitle1\t\t42\t62.0");
        assertThat(result).contains("\ttitle2\t\t2\t6.0");
        assertThat(result).contains("\ttitle3\t\t1\t1.5");
        assertThat(result).contains("Amount owed is 69.5");
        assertThat(result).contains("You earned 4 frequent renter points");

    }


}
