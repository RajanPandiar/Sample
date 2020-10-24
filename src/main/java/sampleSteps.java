import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;
import java.util.Random;

public class sampleSteps {
    private int counter;
    private int result;
    private String api_resource ;
    private Response response;

    @Given("a number")
    public void aNumber(){
        counter = new Random().nextInt();
    }

    @Given("i have an api resource")
    public void apiResource(){
        api_resource = "https://reqres.in/api/users?page=2";
    }

    @When("i add 5 to this number")
    public void addFive(){
        result = counter + 5;
    }

    @When("i make a get call")
    public void makeGetCall() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(api_resource)
                .build();

            response = client.newCall(request).execute();
            System.out.println(response.body().string());


        }


    @Then("diffenence between the number and the result is 5")
    public void diffValidation(){
        assert result - counter == 5;
    }

    @Then("i get a successful response")
    public void reponseIsSuccessful(){
        assert response.code() == 200;
    }
}
