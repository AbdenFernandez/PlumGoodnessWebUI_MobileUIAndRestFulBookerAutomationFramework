package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CreateBookingRequestPojo {

    String firstname;
    String lastname;
    int totalprice;
    boolean depositpaid;
    BookingDate bookingdates;
    String additionalneeds;

}
