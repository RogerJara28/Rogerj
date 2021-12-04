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
import pe.idat.approgerjara.dto.Todo;
import pe.idat.approgerjara.network.RetrofitService;
import retrofit2.Response;

public class TodosFragment extends Fragment {

    private FragmentTodosBinding binding;
    private RecyclerView todosRv;
    private List<Todo> todosImpares;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTodosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        todosRv = binding.todosRv;
        todosRv.setLayoutManager(new LinearLayoutManager(getContext()));

        todosImpares = new ArrayList<>();

        solicitarTodos();

        return root;
    }

    private void solicitarTodos() {
        new Thread(() -> {
            try {
                Response<List<Todo>> response = RetrofitService.api.obtenerTodos().execute();


                if (response.isSuccessful()) {
                    List<Todo> allTodos = response.body();
                    for (Todo todo : allTodos) {
                        if (todo.getUserId() % 2 != 0) {
                            todosImpares.add(todo);
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
        TodosAdapter adapter = new TodosAdapter(todosImpares);
        todosRv.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}