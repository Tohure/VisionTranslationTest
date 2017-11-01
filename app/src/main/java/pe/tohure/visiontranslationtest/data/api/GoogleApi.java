package pe.tohure.visiontranslationtest.data.api;

import java.util.List;

import pe.tohure.visiontranslationtest.data.model.LogoResponse;
import pe.tohure.visiontranslationtest.data.model.TranslationResponse;
import pe.tohure.visiontranslationtest.data.model.VisionRequest;
import pe.tohure.visiontranslationtest.data.model.VisionRespose;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static pe.tohure.visiontranslationtest.BuildConfig.TRANSLATION_CLOUD;
import static pe.tohure.visiontranslationtest.BuildConfig.VISION_CLOUD;

/**
 * Created by tohure on 30/09/17.
 */

public interface GoogleApi {

    @Headers("Content-Type: application/json")
    @POST(VISION_CLOUD)
    Call<VisionRespose> processImage64(@Header("Content-Length") String bodyLength, @Query("key") String apiKey, @Body VisionRequest visionRequest);

    @Headers("Content-Type: application/json")
    @POST(VISION_CLOUD)
    Call<LogoResponse> processLogoImage64(@Header("Content-Length") String bodyLength, @Query("key") String apiKey, @Body VisionRequest visionRequest);

    @Headers("Content-Type: application/json")
    @GET(TRANSLATION_CLOUD)
    Call<TranslationResponse> translateQuery(@Query("key") String apiKey, @Query("source") String source, @Query("target") String target, @Query("q") List<String> queries);
}