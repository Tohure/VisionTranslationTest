package pe.tohure.visiontranslationtest.data.model;

import java.util.List;

/**
 * Created by tohure on 30/09/17.
 */

public class VisionRequest {

    private List<RequestsBean> requests;

    public List<RequestsBean> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestsBean> requests) {
        this.requests = requests;
    }

    public static class RequestsBean {
        /**
         * image : {"content":"Base64 images"}
         * features : [{"type":"LABEL_DETECTION"}]
         */

        private ImageBean image;
        private List<FeaturesBean> features;

        public ImageBean getImage() {
            return image;
        }

        public void setImage(ImageBean image) {
            this.image = image;
        }

        public List<FeaturesBean> getFeatures() {
            return features;
        }

        public void setFeatures(List<FeaturesBean> features) {
            this.features = features;
        }

        public static class ImageBean {
            /**
             * content : Base64 images
             */

            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class FeaturesBean {
            /**
             * type : LABEL_DETECTION
             */

            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
