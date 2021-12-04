package pe.idat.approgerjara.ui.photos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pe.idat.approgerjara.databinding.FragmentPhotosBinding;
import pe.idat.approgerjara.dto.Photo;
import pe.idat.approgerjara.network.RetrofitService;
import retrofit2.Response;

public class PhotosFragment extends Fragment {

    private FragmentPhotosBinding binding;
    private RecyclerView photosRv;
    private List<Photo> photosPares;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPhotosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        photosRv = binding.photosRv;
        photosRv.setLayoutManager(new GridLayoutManager(getContext(), 2));

        photosPares = new ArrayList<>();

        solicitarPhotos();

        return root;
    }

    private void solicitarPhotos() {
        new Thread(() -> {
            try {
                Response<List<Photo>> response = RetrofitService.api.obtenerPhotos().execute();
                if (response.isSuccessful()) {
                    List<Photo> allPhotos = response.body();
                    for (Photo photo : allPhotos) {

                        if (photo.getAlbumId() % 2 == 0) {
                            photosPares.add(photo);
                        }
                    }
                    getActivity().runOnUiThread(this::cargarAdaptador);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void cargarAdaptador() {
        PhotosAdapter adapter = new PhotosAdapter(photosPares);
        photosRv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}