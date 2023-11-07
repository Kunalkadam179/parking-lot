package com.thoughtworks.parkinglot;

import com.thoughtworks.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.parkinglot.exceptions.AlreadyUnparkedException;
import com.thoughtworks.parkinglot.exceptions.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    void shouldParkACarWhenSpaceIsAvailable() throws ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        parkingLot.park(car);

        assertThat(parkingLot.isParked(car), is(true));
    }

    @Test
    void shouldNotParkACarWhenSpaceIsNotAvailable() throws ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Parkable car1 = new Car();
        Parkable car2 = new Car();

        parkingLot.park(car1);

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(car2));
    }

    @Test
    void shouldNotParkACarWhenItIsAlreadyParked() throws ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car = new Car();

        parkingLot.park(car);

        assertThrows(AlreadyParkedException.class, () -> parkingLot.park(car));
    }

    @Test
    void shouldUnparkACarWhenItIsParked() throws ParkingLotFullException, AlreadyParkedException, AlreadyUnparkedException{
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car1 = new Car();
        parkingLot.park(car1);

        parkingLot.unpark(car1);

        assertThat(parkingLot.isParked(car1), is(false));


    }

    @Test
    void shouldNotUnparkACarWhenItISAlreadyUnparked() throws  AlreadyUnparkedException{
        ParkingLot parkingLot = new ParkingLot(2);
        Parkable car1 = new Car();

        assertThrows(AlreadyUnparkedException.class, () -> {parkingLot.unpark(car1);});
    }


}
