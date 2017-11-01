package pe.tohure.visiontranslationtest.data.model;

import java.util.List;

/**
 * Created by tohure on 01/10/17.
 */

public class TranslationResponse {

    /**
     * data : {"translations":[{"translatedText":"...."},{"translatedText":"...."}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TranslationsBean> translations;

        public List<TranslationsBean> getTranslations() {
            return translations;
        }

        public void setTranslations(List<TranslationsBean> translations) {
            this.translations = translations;
        }

        public static class TranslationsBean {
            /**
             * translatedText : ....
             */

            private String translatedText;

            public String getTranslatedText() {
                return translatedText;
            }

            public void setTranslatedText(String translatedText) {
                this.translatedText = translatedText;
            }
        }
    }
}
