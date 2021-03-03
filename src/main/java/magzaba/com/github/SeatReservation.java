package magzaba.com.github;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

//zadanie 1
// zaimplementuj klasę Compartment używając wybranej kolekcji
// miejsca w przedziale ponumerowane są następująco:
//      okno
//  1           8
//  2           7
//  3           6
//  4           5
//      drzwi

class Compartment {
    private Map<Seat, Passenger> seats;

    public Compartment() {
        seats = new TreeMap<Seat, Passenger>();
        seats.put(new Seat(1, Preferences.WINDOW), null);
        seats.put(new Seat(2, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(3, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(4, Preferences.DOOR), null);
        seats.put(new Seat(5, Preferences.DOOR), null);
        seats.put(new Seat(6, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(7, Preferences.UNSPECIFIED), null);
        seats.put(new Seat(8, Preferences.WINDOW), null);
    }


//zadanie 2
    // zaimplemetuj metodę bookSeat() uwzględniając trzy warianty:
    // miejsce jest dostępne
    // miejsce jest zajęte
    // brak wolnych miejsc

    /**
     * Books a chosen seat and confirms reservation,  denies if not possible
     *
     * @param passenger
     * @param preferences
     * @return different messages depending on the seat availability
     * @see BookingMessage
     */
    String bookSeat(Passenger passenger, Preferences preferences) {
        if (isFull()) {
            return BookingMessage.OVERLOADED.toString();
        }
        return getPreferableAvailable(preferences) == null ? BookingMessage.UNAVAILABLE.toString() : BookingMessage.SUCCESSFUL.toString();
    }

    private boolean isSeatAvailable(Seat seat) {
        return seats.get(seat) == null;
    }

    private Seat getPreferableAvailable(Preferences preferences) {
        if (preferences == Preferences.UNSPECIFIED) {
            return null;
        }
        var available = seats.entrySet().stream()
                .filter(allSeats -> (allSeats.getKey().checkLocation() == preferences) && (allSeats.getValue() == null))
                .map(preferableSeats -> preferableSeats.getKey())
                .findFirst().orElseGet(null);
        return available;

    }


    private boolean isFull() {
        return (!seats.containsValue(null));
    }


//zadanie 3
    // tworzenie raportów w formie listy Stringów - raporty mają wypisać:
    //  miejsca wolne
    // miejsca zajęte
    // lista pasażerów z miejscem przez nich zarezerwowanym
    // listy powinny być posortowane w kolejności zgodnej z numeracją miejsc, tj. od miejsca 1 do 8

    /**
     * @return list of available seats in ascending seat number order
     */
    List<String> listAvailable() {
        return seats.entrySet().stream()
                .filter(allSeats -> allSeats.getValue() == null)
                .map(freeSeats -> freeSeats.getKey().toString())
                .collect(Collectors.toList());
    }

    /**
     * @return list of reserved seats in ascending seat number order
     */
    List<String> listReserved() {
        return seats.entrySet().stream()
                .filter(allSeats -> allSeats.getValue() != null)
                .map(bookedSeats -> bookedSeats.getKey().toString())
                .collect(Collectors.toList());
    }

    /**
     * @return list of passengers with booked seats in ascending order of seat numbers
     */
    List<String> listPassengers() {
        return seats.entrySet().stream()
                .filter(allSeats -> allSeats.getValue() != null)
                .map(bookedSeats -> bookedSeats.getValue().toString() + " has booked " + bookedSeats.getKey().toString())
                .collect(Collectors.toList());

    }
}

enum BookingMessage {
    SUCCESSFUL("Successfull booking"), UNAVAILABLE("Chosen seat unavailable"), OVERLOADED("No free seats in this compartment");
    private final String message;

    BookingMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}


enum Preferences {
    WINDOW, DOOR, UNSPECIFIED
}


class Seat implements Comparable<Seat> {

    private Preferences preferences;
    private int number;

    public Seat(int number, Preferences preferences) {
        this.number = number;
        this.preferences = preferences;

    }

    @Override
    public String toString() {
        return "seat number:" + number + ", " + preferences.toString();
    }

    public int compareTo(Seat seat) {
        return Integer.compare(number, seat.number);
    }

    public Preferences checkLocation() {
        return preferences;
    }
}


class Passenger {
    String name;

    public Passenger(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
