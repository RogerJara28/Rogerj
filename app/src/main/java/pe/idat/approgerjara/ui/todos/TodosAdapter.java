package pe.idat.approgerjara.ui.todos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pe.idat.approgerjara.R;
import pe.idat.approgerjara.dto.Objeto1;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodosViewHolder> {

    private List<Objeto1> todos;
    private boolean shiny;


    public TodosAdapter(List<Objeto1> todos,boolean shiny) {
        this.shiny = shiny;
        this.todos = todos;
    }

    @NonNull
    @Override
    public TodosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);
        return new TodosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodosViewHolder holder, int position) {
        Objeto1 currentTodo = todos.get(position);
        String poke = currentTodo.getUrl().substring(0,currentTodo.getUrl().length()-1);
        int poken = poke.lastIndexOf("/");
        String poket = poke.substring(poken);
        holder.title.setText(currentTodo.getName());
        String url = "";
        if  (shiny) {
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/";

        }
        else
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";

       Picasso.get().load(url+poket+".png").into(holder.image);

    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class TodosViewHolder extends RecyclerView.ViewHolder {

        TextView userId, id, title, completed ;
        ImageView image;

        public TodosViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.pokeimg);
            title = itemView.findViewById(R.id.todo_title);
        }
    }
}
