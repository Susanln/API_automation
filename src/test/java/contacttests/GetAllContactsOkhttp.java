package contacttests;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.GetAllContactsDto;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContactsOkhttp {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImFzZGhnZkBnbWFpbC5jb20ifQ.NkiH9nGd4zli4gvYVxCvm0tOcmbAchRsmMGhIb9G76o";

    @Test
    public void getAllContactsSuccess() throws IOException {
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/contact")
                .addHeader("Authorization",token).build();
        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.code(),200);

        GetAllContactsDto contactsDto = gson.fromJson(response.body().string(), GetAllContactsDto.class);
        List<ContactDto> list = contactsDto.getContacts();
        for(ContactDto c: list)
        {
            System.out.println(c.getEmail());
            System.out.println(c.getId());
            System.out.println("*************");
        }



    }


}
