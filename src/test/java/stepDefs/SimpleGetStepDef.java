package stepDefs;

import apis.GetBookingAPI;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import pojo.request.CreateBookingRequest;
import sharedContext.SharedStepContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimpleGetStepDef {


    private GetBookingAPI getBookingAPI;
    private final SharedStepContext sharedStepContext;


    public SimpleGetStepDef(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.getBookingAPI = new GetBookingAPI();
    }

    @Given("I prepare a simple HTTP GET request")
    public void iPrepareASimpleHTTPGETRequest() {
        this.getBookingAPI = new GetBookingAPI();
    }

    @When("I send the request to API")
    public void iSendTheRequestToAPI() {
        this.sharedStepContext.apiResponse = this.getBookingAPI.getAllBookingIds();

    }

    @And("We retrieve the booking using bookingId")
    public void weRetrieveTheBookingUsingBookingId() {
        this.sharedStepContext.apiResponse = this.getBookingAPI.getBookingId(this.sharedStepContext.bookingId);
    }

    @When("getBooking API response should have fields same as create request")
    public void getbookingAPIResponseShouldHaveFieldsSameAsCreateRequest() {
        validateRetrieveBookinDetails(this.sharedStepContext.createBookingAPIRequest, this.sharedStepContext.apiResponse);
    }

    private static void validateRetrieveBookinDetails(CreateBookingRequest createBookingRequest, Response response) {
        response
                .then().assertThat().statusCode(200)
                .and().body("firstname", is(equalTo(createBookingRequest.getFirstName())))
                .and().body("lastname", is(equalTo(createBookingRequest.getLastName())))
                .and().body("totalprice", is(equalTo(createBookingRequest.getTotalPrice())))
                .and().body("depositpaid", is(equalTo(createBookingRequest.isDepositPaid())))
                .and().rootPath("bookingdates")
                .and().body("checkin", is(equalTo(createBookingRequest.getBookingDates().getCheckIn())))
                .and().body("checkout", is(equalTo(createBookingRequest.getBookingDates().getCheckOut())))
                .and().detachRootPath("bookingdates");
    }
}
