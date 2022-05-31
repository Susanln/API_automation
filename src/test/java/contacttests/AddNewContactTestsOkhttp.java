package contacttests;

import com.google.gson.Gson;
import dto.ContactDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContactTestsOkhttp {

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFzZGhnZkBnbWFpbC5jb20ifQ.NkiH9nGd4zli4gvYVxCvm0tOcmbAchRsmMGhIb9G76o";

    @Test
    public void addNewContactSuccess() throws IOException {
        int i = (int) (System.currentTimeMillis()/1000);
        ContactDto contactDto = ContactDto.builder().name("Karen").lastName("Potapov")
                .email("jrcac"+i+"@gmail.com")
                .address("Lohnes")
                .phone("7894"+i).description("Haver").build();
        RequestBody body = RequestBody.create(gson.toJson(contactDto),JSON);
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/contact")
                .post(body)
                .addHeader("Authorization",token)
                .build();
        Response response = client.newCall(request).execute();

        Assert.assertEquals(response.code(),200);
        Assert.assertTrue(response.isSuccessful());

        ContactDto contact= gson.fromJson(response.body().string(),ContactDto.class);
        System.out.println(contact.getId());

        Assert.assertEquals(contactDto.getName(),contact.getName());
        Assert.assertEquals(contactDto.getLastName(),contact.getLastName());
        Assert.assertNotEquals(contactDto.getId(),contact.getId());


    }
}
