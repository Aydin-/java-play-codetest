import play.libs.F;
import play.mvc.Http;

import static play.test.Helpers.*;
import static play.test.Helpers.POST;

public class TestUtils {
    public static void run(F.Callback<play.test.TestBrowser> callback) {
        running(testServer(3333, fakeApplication()), HTMLUNIT, callback);
    }

    public static Http.RequestBuilder getDefaultRequest() {
        return new Http.RequestBuilder()
                .method(POST)
                .uri("/customers/sort");
    }
}
