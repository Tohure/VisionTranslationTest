package pe.tohure.visiontranslationtest.view.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import pe.tohure.visiontranslationtest.R;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;
import static pe.tohure.visiontranslationtest.util.Constant.REQUEST_BRAND_CODE;
import static pe.tohure.visiontranslationtest.util.Constant.REQUEST_OBJECTS_CODE;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private TextView textResult;
    private TextView textResultSpanish;
    private ProgressBar progressBar;
    private MainContract.Presenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        textResult = findViewById(R.id.textResult);
        textResultSpanish = findViewById(R.id.textResultSpanish);
        progressBar = findViewById(R.id.progressBar);

        mainPresenter = new MainPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.start();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mainPresenter = checkNotNull(presenter);
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_OBJECTS_CODE);
    }

    public void takeBrand(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_BRAND_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == REQUEST_OBJECTS_CODE || requestCode == REQUEST_BRAND_CODE) && resultCode == RESULT_OK) {

            Bitmap picture = (Bitmap) data.getExtras().get("data");

            ((ImageView) findViewById(R.id.pictureResult)).setImageBitmap(picture);

            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            if (picture != null) {
                picture.compress(Bitmap.CompressFormat.JPEG, 90, byteStream);
            }

            String base64Data = Base64.encodeToString(byteStream.toByteArray(), Base64.URL_SAFE);
            mainPresenter.prepareRequest(base64Data, requestCode);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showQueryResponse(String description) {
        textResult.setText(description);
    }

    @Override
    public void showQueryInSpanish(String description) {
        textResultSpanish.setText(description);
    }
}