package pe.idat.approgerjara.ui.todos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pe.idat.approgerjara.databinding.FragmentTodosBinding;
import pe.idat.approgerjara.dto.Objeto1;
import pe.idat.approgerjara.dto.Objeto1_1;
import pe.idat.approgerjara.network.RetrofitService;
import retrofit2.Response;

public class TodosFragment extends Fragment {

    private FragmentTodosBinding binding;
    private RecyclerView todosRv;
    private List<Objeto1_1> todosImpares;
    private List<Objeto1> photo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTodosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        todosRv = binding.todosRv;
        todosRv.setLayoutManager(new LinearLayoutManager(getContext()));

        todosImpares = new ArrayList<>();
        photo  = new ArrayList<>();


        solicitarTodos();

        return root;
    }

    private void solicitarTodos() {
        new Thread(() -> {
            try {
                Response<Objeto1_1> response = RetrofitService.api.obtenerTodos(-1).execute();


                if (response.isSuccessful()) {
                    Objeto1_1 allTodos = response.body();
                    photo = allTodos.getResults();

                    getActivity().runOnUiThread(this::cargarAdaptador);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void cargarAdaptador() {
        TodosAdapter adapter = new TodosAdapter(photo,false);
        todosRv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }





}