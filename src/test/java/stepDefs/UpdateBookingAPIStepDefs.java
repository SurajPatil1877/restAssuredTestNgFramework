package stepDefs;

import apis.UpdateBookingAPI;
import io.cucumber.java.en.And;
import pojo.dataHandler.BookingDetails;
import pojo.request.CreateBookingRequest;
import sharedContext.SharedStepContext;
import util.ApiRequestHelper;
import util.Helper;
import util.TestDataHelper;

import java.util.Map;

public class UpdateBookingAPIStepDefs {

    private final UpdateBookingAPI updateBookingAPI;
    private final SharedStepContext sharedStepContext;
    private CreateBookingRequest createBookingRequestPojo;

    public UpdateBookingAPIStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.updateBookingAPI = new UpdateBookingAPI();
    }


    @And("we prepare a request for update booking API")
    public void wePrepareARequestForUpdateBookingAPI(Map<String, String> requestDataMap) {
        String checkInPlusDays = TestDataHelper.getFurtureDate(Integer.parseInt(requestDataMap.get("checkInPlusDays")), Helper.DATE_FORMAT.getDateTimeFormatter());
        String checkoutPlusDays = TestDataHelper.getFurtureDate(Integer.parseInt(requestDataMap.get("checkoutPlusDays")), Helper.DATE_FORMAT.getDateTimeFormatter());

        BookingDetails bookingDetails = BookingDetails.builder()
                                                      .firstName(requestDataMap.get("firstName"))
                                                      .lastName(requestDataMap.get("lastName"))
                                                      .checkIn(checkInPlusDays)
                                                      .checkOut(checkoutPlusDays)
                                                      .depositPaid(Boolean.parseBoolean(requestDataMap.get("depositPaid")))
                                                      .totalPrice(Integer.parseInt(requestDataMap.get("totalPrice")))
                                                      .additionalNeeds(requestDataMap.get("additionalNeeds"))
                                                      .build();

        this.createBookingRequestPojo = ApiRequestHelper.getCreateBookingRequestPojo(bookingDetails);
    }

    @And("we send request to update booking API")
    public void weSendRequestToUpdateBookingAPI() {
        this.sharedStepContext.apiResponse = this.updateBookingAPI.updateBooking(
                this.createBookingRequestPojo, this.sharedStepContext.bookingId, "admin", "password123");
    }
}
