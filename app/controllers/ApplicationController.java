package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import model.Customer;
import play.libs.F;
import play.libs.HttpExecution;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.ExecutionContext;

import java.util.Arrays;

public class ApplicationController extends Controller {

    private static final ObjectMapper CUST_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new JodaModule())
            .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

    @BodyParser.Of(BodyParser.Json.class)
    public static Result sortCustomers() {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Missing JSON in request body");
        }

        if(json.findPath("duetime").textValue() == null) {
            return badRequest("Missing parameter [duetime]");
        }

        try {
            Customer[] customers = CUST_MAPPER.treeToValue(json, Customer[].class);
            Arrays.sort(customers);
            return ok(CUST_MAPPER.writeValueAsString(customers)).as("application/json");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return badRequest("Error parsing JSON");
        }
    }

    public static F.Promise<Result> sortCustomersAsync() {
        ExecutionContext myEc = HttpExecution.defaultContext();
        return F.Promise.promise(ApplicationController::sortCustomers, myEc)
                .map((Result result) -> result, myEc);
    }
}
