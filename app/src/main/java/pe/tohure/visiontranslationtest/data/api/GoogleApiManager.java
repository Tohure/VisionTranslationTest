package pe.tohure.visiontranslationtest.data.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static pe.tohure.visiontranslationtest.BuildConfig.URL_BASE_TRANSLATE;
import static pe.tohure.visiontranslationtest.BuildConfig.URL_BASE_VISION;

/**
 * Created by tohure on 30/09/17.
 */

public class GoogleApiManager {

    private static OkHttpClient okHttpClient;

    public static GoogleApi apiVisionManager() {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(URL_BASE_VISION)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return client.create(GoogleApi.class);
    }

    public static GoogleApi apiTranslateManager() {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(URL_BASE_TRANSLATE)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return client.create(GoogleApi.class);
    }

    private static OkHttpClient getClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
        }

        return okHttpClient;
    }
}