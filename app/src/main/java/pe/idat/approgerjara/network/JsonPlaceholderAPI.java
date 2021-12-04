package pe.idat.approgerjara.network;

import java.util.List;

import pe.idat.approgerjara.dto.Photo;
import pe.idat.approgerjara.dto.Todo;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderAPI {

    @GET("todos")
    Call<List<Todo>> obtenerTodos();

    @GET("photos")
    Call<List<Photo>> obtenerPhotos();
}
