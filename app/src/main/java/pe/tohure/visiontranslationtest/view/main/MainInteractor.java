package pe.tohure.visiontranslationtest.view.main;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import pe.tohure.visiontranslationtest.data.api.GoogleApiManager;
import pe.tohure.visiontranslationtest.data.model.LogoResponse;
import pe.tohure.visiontranslationtest.data.model.TranslationResponse;
import pe.tohure.visiontranslationtest.data.model.VisionRequest;
import pe.tohure.visiontranslationtest.data.model.VisionRespose;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pe.tohure.visiontranslationtest.util.Constant.CLOUD_API_KEY;

/**
 * Created by tohure on 01/11/17.
 */

public class MainInteractor {

    static void logoRequest(String base64Data, VisionRequest visionRequest, final MainPresenter mainPresenter) {
        Call<LogoResponse> call = GoogleApiManager.apiVisionManager().processLogoImage64(String.valueOf(base64Data.length()), CLOUD_API_KEY, visionRequest);

        call.enqueue(new Callback<LogoResponse>() {
            @Override
            public void onResponse(@NonNull Call<LogoResponse> call, @NonNull Response<LogoResponse> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getResponses().isEmpty()) {
                        mainPresenter.printLogoResponse(response.body().getResponses().get(0).getLogoAnnotations());
                    } else {
                        Log.e("tohure", "No se hallaron coincidencias");
                    }
                } else {
                    Log.e("tohure", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoResponse> call, @NonNull Throwable t) {
                Log.e("tohure", t.getMessage());
            }
        });
    }

    static void imageRequest(String base64Data, VisionRequest visionRequest, final MainPresenter mainPresenter) {
        Call<VisionRespose> call = GoogleApiManager.apiVisionManager().processImage64(String.valueOf(base64Data.length()), CLOUD_API_KEY, visionRequest);

        call.enqueue(new Callback<VisionRespose>() {
            @Override
            public void onResponse(@NonNull Call<VisionRespose> call, @NonNull Response<VisionRespose> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getResponses().isEmpty()) {
                        mainPresenter.printImageResponse(response.body().getResponses().get(0).getLabelAnnotations());
                    } else {
                        Log.e("tohure", "No se hallaron coincidencias");
                    }
                } else {
                    Log.e("tohure", response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<VisionRespose> call, @NonNull Throwable t) {
                Log.e("tohure", t.getMessage());
            }
        });
    }


    public static void translateResponse(List<String> queries, final MainPresenter mainPresenter) {
        Call<TranslationResponse> call = GoogleApiManager.apiTranslateManager().translateQuery(CLOUD_API_KEY, "en", "es", queries);

        call.enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(@NonNull Call<TranslationResponse> call, @NonNull Response<TranslationResponse> response) {
                if (!response.body().getData().getTranslations().isEmpty()) {
                    mainPresenter.printTranslate(response.body().getData().getTranslations());
                } else {
                    Log.e("tohure", "No se hallaron coincidencias");
                }
            }

            @Override
            public void onFailure(@NonNull Call<TranslationResponse> call, @NonNull Throwable t) {
                Log.e("tohure", t.getMessage());
            }
        });
    }
}
