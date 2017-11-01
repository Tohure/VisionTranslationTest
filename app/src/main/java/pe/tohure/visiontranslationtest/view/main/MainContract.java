package pe.tohure.visiontranslationtest.view.main;

import java.util.List;

import pe.tohure.visiontranslationtest.data.model.LogoResponse;
import pe.tohure.visiontranslationtest.data.model.TranslationResponse;
import pe.tohure.visiontranslationtest.data.model.VisionRequest;
import pe.tohure.visiontranslationtest.data.model.VisionRespose;
import pe.tohure.visiontranslationtest.util.BasePresenter;
import pe.tohure.visiontranslationtest.util.BaseView;

/**
 * Created by tohure on 31/10/17.
 */

interface MainContract {
    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();

        void showQueryResponse(String description);

        void showQueryInSpanish(String description);
    }

    interface Presenter extends BasePresenter {

        void prepareRequest(String base64Data, int requestCode);

        void makeVisionLogoQuery(String base64Data, VisionRequest visionRequest);

        void makeVisionImageQuery(String base64Data, VisionRequest visionRequest);

        void printLogoResponse(List<LogoResponse.ResponsesBean.LogoAnnotationsBean> list);

        void printImageResponse(List<VisionRespose.ResponsesBean.LabelAnnotationsBean> list);

        void translateToSpanish(List<String> list);

        void printTranslate(List<TranslationResponse.DataBean.TranslationsBean> translations);
    }
}
