import org.apache.commons.io.IOUtils;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class IntegrationTest {

    @Test
    public void testSortCustomersDefaultJson() throws Exception {
        TestUtils.run(browser -> {
            String data = IOUtils.toString(this.getClass().getResourceAsStream("customers.json"));
            Http.RequestBuilder request = TestUtils.getDefaultRequest().bodyJson(Json.parse(data));
            Result result = route(request);
            assertThat(result.status()).isEqualTo(Http.Status.OK);
        });
    }

    @Test
    public void testSortCustomersNoJson() throws Exception {
        TestUtils.run(browser -> {
            Http.RequestBuilder request = TestUtils.getDefaultRequest();
            Result result = route(request);
            assertThat(result.status()).isEqualTo(Http.Status.BAD_REQUEST);
            assertThat(result.contentType());
        });
    }

    @Test
    public void testSortCustomersEmptyJson() throws Exception {
        TestUtils.run(browser -> {
            Http.RequestBuilder request = TestUtils.getDefaultRequest().bodyJson(Json.parse("[]"));
            Result result = route(request);
            assertThat(result.status()).isEqualTo(Http.Status.BAD_REQUEST);
        });
    }

}
