package pe.idat.approgerjara.ui.todos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.idat.approgerjara.R;
import pe.idat.approgerjara.dto.Todo;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.TodosViewHolder> {

    private List<Todo> todos;

    public TodosAdapter(List<Todo> todos) {
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
        Todo currentTodo = todos.get(position);
        holder.userId.setText("UserId: " + currentTodo.getUserId());
        holder.id.setText("Id: " + currentTodo.getId());
        holder.title.setText("Title: " + currentTodo.getTitle());
        holder.completed.setText("Completed: " + currentTodo.isCompleted());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    static class TodosViewHolder extends RecyclerView.ViewHolder {

        TextView userId, id, title, completed;

        public TodosViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.todo_user_id);
            id = itemView.findViewById(R.id.todo_id);
            title = itemView.findViewById(R.id.todo_title);
            completed = itemView.findViewById(R.id.todo_completed);
        }
    }
}
