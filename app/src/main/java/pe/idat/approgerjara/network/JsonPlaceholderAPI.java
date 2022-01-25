package pe.idat.approgerjara.network;

import java.util.List;

import pe.idat.approgerjara.dto.Objeto1;
import pe.idat.approgerjara.dto.Objeto1_1;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceholderAPI {

    @GET("pokemon")
    Call<Objeto1_1> obtenerTodos(@Query("limit") int limit);

    @GET("photos")
    Call<List<Objeto1>> obtenerPhotos();
}
