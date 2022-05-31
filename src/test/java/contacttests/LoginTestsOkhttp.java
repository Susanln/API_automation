package contacttests;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestsOkhttp {

    public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder().email("asdhgf@gmail.com")
                .password("Nnoa12345$").build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/login").post(requestBody)
                .build();
        Response response = client.newCall(request).execute();

        AuthResponseDto responseDto = gson.fromJson(response.body().string(), AuthResponseDto.class);
        String token= responseDto.getToken();
        System.out.println(token);


        Assert.assertEquals(response.code(),200);
        Assert.assertTrue(response.isSuccessful());
    }
    @Test
    public void loginWrongEmail() throws IOException {
        AuthRequestDto requestDto = AuthRequestDto.builder().email("asdhgfgmail.com")
                .password("Nnoa12345$").build();
        RequestBody requestBody = RequestBody.create(gson.toJson(requestDto),JSON);
        Request request = new Request.Builder().url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        ErrorDto errorDto = gson.fromJson(response.body().string(), ErrorDto.class);
        String message = errorDto.getMessage();
        Assert.assertEquals(message,"Wrong email format! Example: name@mail.com");


        Assert.assertEquals(response.code(),400);
        Assert.assertFalse(response.isSuccessful());

    }

}
