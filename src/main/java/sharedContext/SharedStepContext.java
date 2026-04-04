package sharedContext;

import io.restassured.response.Response;
import pojo.request.CreateBookingRequest;

public class SharedStepContext {

    public Response apiResponse;
    public int bookingId;
    public CreateBookingRequest createBookingAPIRequest;
}
