package pe.idat.approgerjara;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import pe.idat.approgerjara.databinding.FragmentTomarfotoBinding;


public class MainTomarFoto extends Fragment implements View.OnClickListener {

        Button btnTakePicture, btnSaveImage;
        ImageView imageView;
        Bitmap bitmap;
        FragmentTomarfotoBinding ById;

        private static final int REQUEST_PERMISSION_CAMERA = 100;
        private static final int TAKE_PICTURE = 101;
        private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200;


        public View onCreateView (@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            //UI
            ById=FragmentTomarfotoBinding.inflate(inflater,container,false);
            ById.btnTakePicture.setOnClickListener(this);
            ById.btnSaveImage.setOnClickListener(this);

            return ById.getRoot();
        }


        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.btnTakePicture) {
                checkPermissionCamera();
            }else if (id == R.id.btnSaveImage) {
                checkPermissionStorage();
            }
        }




        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
            if (requestCode == TAKE_PICTURE){
                if (resultCode == Activity.RESULT_OK && data!=null){
                    bitmap = (Bitmap) data.getExtras().get("data");
                    ById.imgPicture.setImageBitmap(bitmap);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (requestCode == REQUEST_PERMISSION_CAMERA) {
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    takePicture();
                }
            }else if (requestCode == REQUEST_PERMISSION_WRITE_STORAGE) {
                if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveImage();
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        private void checkPermissionCamera() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                }else{
                    ActivityCompat.requestPermissions(
                            getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_PERMISSION_CAMERA
                    );
                }
            }else{
                takePicture();

            }
        }

        private void checkPermissionStorage() {
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        saveImage();
                    }else{
                        ActivityCompat.requestPermissions(
                                getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                REQUEST_PERMISSION_WRITE_STORAGE
                        );
                    }
                }else{
                    saveImage();
                }
            }else{
                saveImage();
            }
        }
        private void takePicture(){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getActivity().getPackageManager())!=null){
                startActivityForResult(intent,TAKE_PICTURE);
            }
        }

        private void saveImage() {
            OutputStream fos = null;
            File file = null;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentResolver resolver = getActivity().getContentResolver();
                ContentValues values = new ContentValues();

                String fileName = System.currentTimeMillis() + "image_example";

                values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
                values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
                values.put(MediaStore.Images.Media.IS_PENDING, 1);

                Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
                Uri imageUri = resolver.insert(collection, values);

                try {
                    fos = resolver.openOutputStream(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                values.clear();
                values.put(MediaStore.Images.Media.IS_PENDING, 0);
                resolver.update(imageUri, values, null, null);
            } else {
                String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

                String fileName = System.currentTimeMillis() + ".jpg";

                file = new File(imageDir, fileName);

                try {
                    fos = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            boolean saved = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            if (saved) {
                Toast.makeText(getContext(), "Picture was saved succesfully", Toast.LENGTH_SHORT).show();
            }

            if (fos!= null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (file!=null) { //API < 29
                MediaScannerConnection.scanFile(getContext(), new String[]{file.toString()}, null, null);
            }
        }
    }




