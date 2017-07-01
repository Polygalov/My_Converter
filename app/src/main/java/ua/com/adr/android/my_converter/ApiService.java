package ua.com.adr.android.my_converter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiService {
    @GET
    Call<ExchangeResponse> getExchange(@Url String url);
}
