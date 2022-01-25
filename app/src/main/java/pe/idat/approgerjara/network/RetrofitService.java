package pe.idat.approgerjara.network;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";

        //"https://jsonplaceholder.typicode.com/";//

    private static final Moshi moshi = new Moshi.Builder().build();

    private static final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .baseUrl(BASE_URL)
            .build();

    public static JsonPlaceholderAPI api = retrofit.create(JsonPlaceholderAPI.class);
}
