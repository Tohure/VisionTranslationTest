package pe.tohure.visiontranslationtest.view.main;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import pe.tohure.visiontranslationtest.data.model.LogoResponse;
import pe.tohure.visiontranslationtest.data.model.TranslationResponse;
import pe.tohure.visiontranslationtest.data.model.VisionRequest;
import pe.tohure.visiontranslationtest.data.model.VisionRespose;

import static pe.tohure.visiontranslationtest.util.Constant.REQUEST_OBJECTS_CODE;

/**
 * Created by tohure on 31/10/17.
 */

public class MainPresenter implements MainContract.Presenter {

    @NonNull
    private final MainContract.View mainView;

    MainPresenter(@NonNull MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Override
    public void start() {
        this.mainView.setPresenter(this);
    }

    @Override
    public void prepareRequest(String base64Data, int requestCode) {
        VisionRequest.RequestsBean.ImageBean imageBean = new VisionRequest.RequestsBean.ImageBean();
        imageBean.setContent(base64Data);

        VisionRequest.RequestsBean.FeaturesBean featuresBean = new VisionRequest.RequestsBean.FeaturesBean();

        if (requestCode == REQUEST_OBJECTS_CODE) {
            featuresBean.setType("LABEL_DETECTION");
        } else {
            featuresBean.setType("LOGO_DETECTION");
        }

        List<VisionRequest.RequestsBean.FeaturesBean> featuresBeanList = new ArrayList<>();
        featuresBeanList.add(featuresBean);

        VisionRequest.RequestsBean requestsBean = new VisionRequest.RequestsBean();
        requestsBean.setImage(imageBean);
        requestsBean.setFeatures(featuresBeanList);

        List<VisionRequest.RequestsBean> list = new ArrayList<>();
        list.add(requestsBean);

        VisionRequest visionRequest = new VisionRequest();
        visionRequest.setRequests(list);

        if (requestCode == REQUEST_OBJECTS_CODE) {
            makeVisionImageQuery(base64Data, visionRequest);
        } else {
            makeVisionLogoQuery(base64Data, visionRequest);
        }
    }

    @Override
    public void makeVisionLogoQuery(String base64Data, VisionRequest visionRequest) {
        mainView.showLoading();
        MainInteractor.logoRequest(base64Data, visionRequest, this);
    }

    @Override
    public void makeVisionImageQuery(String base64Data, VisionRequest visionRequest) {
        mainView.showLoading();
        MainInteractor.imageRequest(base64Data, visionRequest, this);
    }

    @Override
    public void printLogoResponse(List<LogoResponse.ResponsesBean.LogoAnnotationsBean> logoAnnotations) {

        StringBuilder features = new StringBuilder();
        List<String> queriesToTranslate = new ArrayList<>();

        if (logoAnnotations != null && !logoAnnotations.isEmpty()) {
            for (LogoResponse.ResponsesBean.LogoAnnotationsBean label : logoAnnotations) {
                features.append("> ").append(label.getDescription()).append("\n");
                queriesToTranslate.add(label.getDescription());
            }

            mainView.showQueryResponse(features.toString());
            translateToSpanish(queriesToTranslate);

        } else {
            mainView.showQueryResponse("Not Result");
            mainView.showQueryInSpanish("No se encontraron Resultados");
            mainView.hideLoading();
        }
    }

    @Override
    public void printImageResponse(List<VisionRespose.ResponsesBean.LabelAnnotationsBean> labelAnotations) {

        StringBuilder features = new StringBuilder();
        List<String> queriesToTranslate = new ArrayList<>();

        for (VisionRespose.ResponsesBean.LabelAnnotationsBean label : labelAnotations) {
            features.append("> ").append(label.getDescription()).append("\n");
            queriesToTranslate.add(label.getDescription());
        }

        mainView.showQueryResponse(features.toString());
        translateToSpanish(queriesToTranslate);
    }


    @Override
    public void translateToSpanish(List<String> queries) {
        MainInteractor.translateResponse(queries, this);
    }

    @Override
    public void printTranslate(List<TranslationResponse.DataBean.TranslationsBean> translations) {
        StringBuilder translationTring = new StringBuilder();

        for (TranslationResponse.DataBean.TranslationsBean translate : translations) {
            translationTring.append("> ").append(translate.getTranslatedText()).append("\n");
        }
        mainView.showQueryInSpanish(translationTring.toString());
        mainView.hideLoading();
    }
}