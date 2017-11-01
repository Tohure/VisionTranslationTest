package pe.tohure.visiontranslationtest.data.model;

import java.util.List;

/**
 * Created by tohure on 30/09/17.
 */

public class VisionRespose {

    private List<ResponsesBean> responses;

    public List<ResponsesBean> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponsesBean> responses) {
        this.responses = responses;
    }

    public static class ResponsesBean {
        private List<LabelAnnotationsBean> labelAnnotations;

        public List<LabelAnnotationsBean> getLabelAnnotations() {
            return labelAnnotations;
        }

        public void setLabelAnnotations(List<LabelAnnotationsBean> labelAnnotations) {
            this.labelAnnotations = labelAnnotations;
        }

        public static class LabelAnnotationsBean {
            /**
             * mid : /m/id1
             * description : label1
             * score : 0.91
             */

            private String mid;
            private String description;
            private double score;

            public String getMid() {
                return mid;
            }

            public void setMid(String mid) {
                this.mid = mid;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }
        }
    }
}