package cities.service.exceptions;

import com.roman.shilov.hw22.travelagency.common.buisness.exceptions.BasicTravelCheckedException;

public class CityIsContainedInSomeOrdersException extends BasicTravelCheckedException {
    public CityIsContainedInSomeOrdersException(String message, int code) {
        super(message, code);
    }
}
