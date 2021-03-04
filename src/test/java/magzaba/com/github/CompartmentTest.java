package magzaba.com.github;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.TreeMap;

import static org.testng.Assert.*;
@Test(groups = "Tasks")
public class CompartmentTest {

    @DataProvider
    Object[] preferences () {
        return Preferences.values();
    }

@Test(dataProvider = "preferences")
public void shouldReturnProperMessageWhenSuccessfullyReservedSeat(Preferences preferences){
    //given
    var seats = new TreeMap<Seat, Passenger>();
    seats.put(new Seat(1, Preferences.WINDOW), new Passenger("Ann Black"));
    seats.put(new Seat(2, Preferences.UNSPECIFIED), new Passenger("Filip Black"));
    seats.put(new Seat(3, Preferences.UNSPECIFIED), null);
    seats.put(new Seat(4, Preferences.DOOR), null);
    seats.put(new Seat(5, Preferences.DOOR), null);
    seats.put(new Seat(6, Preferences.UNSPECIFIED), null);
    seats.put(new Seat(7, Preferences.UNSPECIFIED), null);
    seats.put(new Seat(8, Preferences.WINDOW), null);
    var compartment = new Compartment(seats);

    //when
    var actual = compartment.bookSeat(new Passenger("Molly Grey"), preferences);

    //then
    assertEquals(actual,BookingMessage.SUCCESSFUL.toString());
}

    @DataProvider
    Object[] specificPreferences () {
        return new Object[] {Preferences.WINDOW, Preferences.DOOR};
    }
    @Test(dataProvider = "specificPreferences")
    public void shouldReturnProperMessageWhenPrefferedSeatUnavailable(Preferences preferences){
        //given
        var seats = new TreeMap<Seat, Passenger>();
        seats.put(new Seat(1, Preferences.WINDOW), new Passenger("Ann Black"));
        seats.put(new Seat(2, Preferences.UNSPECIFIED), new Passenger("Filip Black"));
        seats.put(new Seat(3, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(4, Preferences.DOOR), new Passenger("Chris Brown"));
        seats.put(new Seat(5, Preferences.DOOR), new Passenger("Mary Brown"));
        seats.put(new Seat(6, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(7, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(8, Preferences.WINDOW), new Passenger("Jane Red"));
        var compartment = new Compartment(seats);

        //when
        var actual = compartment.bookSeat(new Passenger("Molly Grey"), preferences);

        //then
        assertEquals(actual,BookingMessage.UNAVAILABLE.toString());
    }

    @Test(dataProvider = "preferences")
    public void shouldReturnProperMessageWhenAllSeatsUnavailable(Preferences preferences){
        //given
        var seats = new TreeMap<Seat, Passenger>();
        seats.put(new Seat(1, Preferences.WINDOW), new Passenger("Ann Black"));
        seats.put(new Seat(2, Preferences.UNSPECIFIED), new Passenger("Filip Black"));
        seats.put(new Seat(3, Preferences.UNSPECIFIED), new Passenger("Clara White"));
        seats.put(new Seat(4, Preferences.DOOR), new Passenger("Chris Brown"));
        seats.put(new Seat(5, Preferences.DOOR), new Passenger("Mary Brown"));
        seats.put(new Seat(6, Preferences.UNSPECIFIED), new Passenger("Sara Green"));
        seats.put(new Seat(7, Preferences.UNSPECIFIED), new Passenger("Tom Green"));
        seats.put(new Seat(8, Preferences.WINDOW), new Passenger("Jane Red"));
        var compartment = new Compartment(seats);

        //when
        var actual = compartment.bookSeat(new Passenger("Molly Grey"), preferences);

        //then
        assertEquals(actual,BookingMessage.OVERLOADED.toString());
    }

    public void shouldListReservedSeatsCorrectly(){
        //given
        var seats = new TreeMap<Seat, Passenger>();
        seats.put(new Seat(1, Preferences.WINDOW), new Passenger("Ann Black"));
        seats.put(new Seat(2, Preferences.UNSPECIFIED), new Passenger("Filip Black"));
        seats.put(new Seat(3, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(4, Preferences.DOOR), new Passenger("Chris Brown"));
        seats.put(new Seat(5, Preferences.DOOR), new Passenger("Mary Brown"));
        seats.put(new Seat(6, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(7, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(8, Preferences.WINDOW), new Passenger("Jane Red"));
        var compartment = new Compartment(seats);
        var expected = List.of(new Seat(1, Preferences.WINDOW).toString(),
                new Seat(2, Preferences.UNSPECIFIED).toString(),
                new Seat(4, Preferences.DOOR).toString(),
                new Seat(5, Preferences.DOOR).toString(),
                new Seat(8, Preferences.WINDOW).toString());

        //when
        var actual= compartment.listReserved();

        //then
        assertEquals(actual,expected);
    }

    public void shouldListFreeSeatsCorrectly(){
        //given
        var seats = new TreeMap<Seat, Passenger>();
        seats.put(new Seat(1, Preferences.WINDOW), new Passenger("Ann Black"));
        seats.put(new Seat(2, Preferences.UNSPECIFIED), new Passenger("Filip Black"));
        seats.put(new Seat(3, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(4, Preferences.DOOR), new Passenger("Chris Brown"));
        seats.put(new Seat(5, Preferences.DOOR), new Passenger("Mary Brown"));
        seats.put(new Seat(6, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(7, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(8, Preferences.WINDOW), new Passenger("Jane Red"));
        var compartment = new Compartment(seats);
        var expected = List.of(new Seat(3, Preferences.UNSPECIFIED).toString(),
                new Seat(6, Preferences.UNSPECIFIED).toString(),
                new Seat(7, Preferences.UNSPECIFIED).toString());

        //when
        var actual= compartment.listAvailable();

        //then
        assertEquals(actual,expected);
    }
}


