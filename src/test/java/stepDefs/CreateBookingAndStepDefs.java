package stepDefs;

import apis.CreateBookingAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.dataHandler.BookingDetails;
import pojo.request.CreateBookingRequest;
import sharedContext.SharedStepContext;
import util.ApiRequestHelper;
import util.Helper;
import util.TestDataHelper;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;


public class CreateBookingAndStepDefs {

    private final CreateBookingAPI createBookingAPI;
    private final SharedStepContext sharedStepContext;
    private CreateBookingRequest createBookingRequestPojo;
    Response createBookingResponse;

    public CreateBookingAndStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.createBookingAPI = new CreateBookingAPI();
    }

    @Given("We have a valid request for  create booking with following params")
    public void weHaveAValidRequestForCreateBooking(List<Map<String, String>> requestDataList) {
        Map<String, String> requestDataMap = requestDataList.getFirst();

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


    @Given("We have a valid request for  create booking with following params as Map and total price {int}")
    public void weHaveAValidRequestForCreateBookingParamsAsMapAndInt(int totalPrice, Map<String, String> requestDataMap) {

        String checkInPlusDays = TestDataHelper.getFurtureDate(Integer.parseInt(requestDataMap.get("checkInPlusDays")), Helper.DATE_FORMAT.getDateTimeFormatter());
        String checkoutPlusDays = TestDataHelper.getFurtureDate(Integer.parseInt(requestDataMap.get("checkoutPlusDays")), Helper.DATE_FORMAT.getDateTimeFormatter());

        BookingDetails bookingDetails = BookingDetails.builder()
                                                      .firstName(requestDataMap.get("firstName"))
                                                      .lastName(requestDataMap.get("lastName"))
                                                      .checkIn(checkInPlusDays)
                                                      .checkOut(checkoutPlusDays)
                                                      .depositPaid(Boolean.parseBoolean(requestDataMap.get("depositPaid")))
                                                      .totalPrice(totalPrice)
                                                      .additionalNeeds(requestDataMap.get("additionalNeeds"))
                                                      .build();

        this.createBookingRequestPojo = ApiRequestHelper.getCreateBookingRequestPojo(bookingDetails);
        this.sharedStepContext.createBookingAPIRequest = this.createBookingRequestPojo;
    }

    @When("We send request to create booking API")
    public void weSendRequestToCreateBookingAPI() {
        this.createBookingResponse = this.createBookingAPI.createNewBooking(this.createBookingRequestPojo);
        this.sharedStepContext.apiResponse = this.createBookingResponse;
    }

    @And("Create booking API response has valid bookingId")
    public void createBookingAPIResponseHasValidBookingId() {
        this.createBookingResponse.then().body("bookingid", is(greaterThan(0)));

    }

    @When("bookingId has been saved in shared context")
    public void bookingidHasBeenSavedInSharedContext() {
        this.sharedStepContext.bookingId = this.createBookingResponse.jsonPath().getInt("bookingid");
    }
}
