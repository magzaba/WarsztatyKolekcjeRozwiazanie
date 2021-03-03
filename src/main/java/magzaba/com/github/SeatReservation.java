package magzaba.com.github;

import java.io.PrintStream;
import java.util.Map;

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
private  Map<Seat,Passenger> seats;

    public Compartment(Map<Seat, Passenger> seats) {
        this.seats = seats;
    }

    boolean bookSeat(Passenger passenger, Preferences preferences){
    return false;}

    boolean isFull(){
        return false;
    }
}



//zadanie 3
// spróbuj dokonać kolejnych rezerwacji:
// Peter Cole - przy oknie
// Ann Duke - przy drzwiach
// John Duke - przy drzwiach
// Olaf Stone - bez preferencji
// Clara Stone - bez preferencji
// Richard Brown - bez preferencji
// Stephen White - bez preferencji
// Charles Black - bez preferencji
// Odmów klientowi rezerwacji miejsca niedostępnego:
//          - wyświetl informację "Chosen seat is unavailable."
//          - wyświetl mu listę miejsc dostępnych w postaci:
//          "Dostępne miejsca: {Seat
// Po przekroczeniu limitu miejsc w przedziale wyświetl informację "No available seats in this compartment"

class SeatReservation {
final MessagePrinter printer = new MessagePrinter(System.out);

    public static void main(String[] args) {

    }







}


enum Preferences {
    WINDOW, DOOR, UNSPECIFIED
}


class Seat {

    private Preferences preferences;
    private int number;

    public Seat(Preferences preferences, int number) {
        this.preferences = preferences;
        this.number = number;
    }

    @Override
    public String toString() {
        return "seat number:"+number+", "+preferences.toString();
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

class MessagePrinter{
    private final PrintStream out;

    public MessagePrinter(PrintStream out) {
        this.out = out;
    }

    void print(String msg){
        out.println(msg);
    }
}
