package contacttests;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.DeleteContactDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIdOkhttp {
    int id;
    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFzZGhnZkBnbWFpbC5jb20ifQ.NkiH9nGd4zli4gvYVxCvm0tOcmbAchRsmMGhIb9G76o";

    @BeforeMethod
    public void preCondition() throws IOException {
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
        ContactDto contact= gson.fromJson(response.body().string(),ContactDto.class);
        id= contact.getId();
    }
    @Test
    public void deleteContactByIdSuccess() throws IOException {
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/contact/"+id)
                .delete()
                .addHeader("Authorization",token).build();
        Response response =client.newCall(request).execute();
        Assert.assertEquals(response.code(),200);
        DeleteContactDto deleteContactDto = gson.fromJson(response.body().string(),DeleteContactDto.class);
        System.out.println(deleteContactDto);

    }

}
