package stepDefs;

import apis.DeleteBookingAPI;
import io.cucumber.java.en.And;
import sharedContext.SharedStepContext;

public class DeleteBookingAPIStepDefs {
    private final DeleteBookingAPI deleteBookingAPI;
    private final SharedStepContext sharedStepContext;

    public DeleteBookingAPIStepDefs(SharedStepContext sharedStepContext) {
        this.sharedStepContext = sharedStepContext;
        this.deleteBookingAPI = new DeleteBookingAPI();
    }


    @And("we send request to delete booking API")
    public void weSendRequestToDeleteBookingAPI() {
        this.sharedStepContext.apiResponse = this.deleteBookingAPI
                .deleteBookingById(this.sharedStepContext.bookingId, "admin", "password123");
    }
}
