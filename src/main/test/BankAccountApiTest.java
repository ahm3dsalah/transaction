import com.google.gson.Gson;
import com.test.api.Main;
import com.test.api.frontend.views.BankAccountView;
import com.test.api.frontend.views.BankTransactionRequestView;
import com.test.api.frontend.views.CreateBankAccountRequestView;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;


public class BankAccountApiTest {


    @BeforeClass
    public static void beforeClass() {
        Main.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void test() {

        Assert.assertTrue(true);
    }

    @Test
    public void createNewBankAccount() {

        //ARRANGE
        CreateBankAccountRequestView newBankAccount = new CreateBankAccountRequestView();
        newBankAccount.setBalance(new BigDecimal("2000"));

        //ACT
        TestResponse res = postRequest("/bankAccount", newBankAccount);
        BankAccountView bankAccountView = new Gson().fromJson(res.body, BankAccountView.class);

        //ASSERT
        assertEquals(200, res.status);
        assertEquals(bankAccountView.getBalance(), new BigDecimal("2000"));

    }


    @Test
    public void whenGetBankAccountById_thenReturnIt() {
        //PREPARE
        CreateBankAccountRequestView newBankAccount = new CreateBankAccountRequestView();
        newBankAccount.setBalance(new BigDecimal("2000"));

        TestResponse res = postRequest("/bankAccount", newBankAccount);
        BankAccountView newBankAccountView = new Gson().fromJson(res.body, BankAccountView.class);

        //ACT
        TestResponse response = getRequest("/bankAccount/" + newBankAccountView.getId());
        BankAccountView getBankAccountView = new Gson().fromJson(response.body, BankAccountView.class);

        assertEquals(getBankAccountView.getId(), newBankAccountView.getId());
        assertTrue(getBankAccountView.getBalance().compareTo(newBankAccountView.getBalance()) == 0);


    }

    @Test
    public void whenCreateBankTransaction_ThenUpdateBankAccount() {
        //PREPARE
        CreateBankAccountRequestView newBankAccount1 = new CreateBankAccountRequestView();
        newBankAccount1.setBalance(new BigDecimal("2000"));

        CreateBankAccountRequestView newBankAccount2 = new CreateBankAccountRequestView();
        newBankAccount2.setBalance(new BigDecimal("2000"));

        TestResponse res = postRequest("/bankAccount", newBankAccount1);
        BankAccountView newBankAccountResponseView1 = new Gson().fromJson(res.body, BankAccountView.class);

        res = postRequest("/bankAccount", newBankAccount2);
        BankAccountView newBankAccountResponseView2 = new Gson().fromJson(res.body, BankAccountView.class);

        // execute transaction
        BankTransactionRequestView transactionRequestView = new BankTransactionRequestView();
        transactionRequestView.setFromBankAccountId(newBankAccountResponseView1.getId());
        transactionRequestView.setToBankAccountId(newBankAccountResponseView2.getId());
        transactionRequestView.setTransactionAmount(new BigDecimal("500"));

        postRequest("/transaction", transactionRequestView);


        //ACT

        //get bank accounts after update
        TestResponse response = getRequest("/bankAccount/" + newBankAccountResponseView1.getId());
        BankAccountView bankAccountView1AfterTransaction = new Gson().fromJson(response.body, BankAccountView.class);

        response = getRequest("/bankAccount/" + newBankAccountResponseView2.getId());
        BankAccountView bankAccountView2AfterTransaction = new Gson().fromJson(response.body, BankAccountView.class);

        assertTrue(bankAccountView1AfterTransaction.getBalance().compareTo(new BigDecimal("1500")) == 0);
        assertTrue(bankAccountView2AfterTransaction.getBalance().compareTo(new BigDecimal("2500")) == 0);



    }

    private TestResponse postRequest(String path, Object requestObject) {
        try {


            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = getJsonRequest(requestObject).getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private TestResponse getRequest(String path) {
        try {


            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            //connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static String getJsonRequest(Object requestObject) {
        return new Gson().toJson(requestObject);
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
