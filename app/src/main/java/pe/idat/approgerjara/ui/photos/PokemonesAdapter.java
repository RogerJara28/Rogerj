package pe.idat.approgerjara.ui.photos;

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

public class PokemonesAdapter extends RecyclerView.Adapter<PokemonesAdapter.PhotosViewHolder> {

    private List<Objeto1> photos;

    public PokemonesAdapter(List<Objeto1> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        Objeto1 currentPhoto = photos.get(position);
        holder.name.setText("name: " + currentPhoto.getName());
        Picasso.get().load(currentPhoto.getUrl()).into(holder.url);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class PhotosViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView url;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
