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
import pe.idat.approgerjara.dto.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<Photo> photos;

    public PhotosAdapter(List<Photo> photos) {
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
        Photo currentPhoto = photos.get(position);
        holder.albumId.setText("Album Id: " + currentPhoto.getAlbumId());
        holder.id.setText("Id: " + currentPhoto.getId());
        holder.title.setText("Title: " + currentPhoto.getTitle());
        Picasso.get().load(currentPhoto.getThumbnailUrl()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class PhotosViewHolder extends RecyclerView.ViewHolder {

        TextView albumId, id, title;
        ImageView thumbnail;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);

            albumId = itemView.findViewById(R.id.photo_album_id);
            id = itemView.findViewById(R.id.photo_id);
            title = itemView.findViewById(R.id.photo_title);
            thumbnail = itemView.findViewById(R.id.photo_thumbnail);
        }
    }
}
