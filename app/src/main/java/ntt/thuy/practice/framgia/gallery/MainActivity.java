package ntt.thuy.practice.framgia.gallery;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private static final int SPAN_COUNT = 2;
    private static final int REQUEST_CODE_PERMISSION = 100;

    private RecyclerView mListImage;
    private ImageAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private ArrayList<String> mImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        requestPermission();
    }

    private void initializeView() {
        mImages = new ArrayList<>();
        mListImage = findViewById(R.id.recycler_images);
        mAdapter = new ImageAdapter(mImages, getApplicationContext());
        mListImage.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        mListImage.setAdapter(mAdapter);
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                getData();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{
                        READ_EXTERNAL_STORAGE
                }, REQUEST_CODE_PERMISSION);
            }
        } else getData();
    }

    private void getData() {
        new MyAsyncTask().execute();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getData();
            } else {
                Toast.makeText(this, R.string.permission_notification, Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage(getString(R.string.message_progress_dialog));
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            File file = new File(Constants.PATH);
            GetImages.getAllImages(file, mImages);
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mProgressDialog.dismiss();
            mAdapter.setList(mImages);
        }
    }
}
