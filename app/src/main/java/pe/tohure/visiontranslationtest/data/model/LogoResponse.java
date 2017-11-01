package pe.tohure.visiontranslationtest.data.model;

import java.util.List;

/**
 * Created by tohure on 21/10/17.
 */

public class LogoResponse {
    private List<ResponsesBean> responses;

    public List<ResponsesBean> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponsesBean> responses) {
        this.responses = responses;
    }

    public static class ResponsesBean {
        private List<LogoAnnotationsBean> logoAnnotations;

        public List<LogoAnnotationsBean> getLogoAnnotations() {
            return logoAnnotations;
        }

        public void setLogoAnnotations(List<LogoAnnotationsBean> logoAnnotations) {
            this.logoAnnotations = logoAnnotations;
        }

        public static class LogoAnnotationsBean {
            /**
             * mid : /m/045c7b
             * description : Google
             * score : 0.32291126
             * boundingPoly : {"vertices":[{"x":63,"y":18},{"x":123,"y":18},{"x":123,"y":38},{"x":63,"y":38}]}
             */

            private String mid;
            private String description;
            private double score;
            private BoundingPolyBean boundingPoly;

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

            public BoundingPolyBean getBoundingPoly() {
                return boundingPoly;
            }

            public void setBoundingPoly(BoundingPolyBean boundingPoly) {
                this.boundingPoly = boundingPoly;
            }

            public static class BoundingPolyBean {
                private List<VerticesBean> vertices;

                public List<VerticesBean> getVertices() {
                    return vertices;
                }

                public void setVertices(List<VerticesBean> vertices) {
                    this.vertices = vertices;
                }

                public static class VerticesBean {
                    /**
                     * x : 63
                     * y : 18
                     */

                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }

                    public void setX(int x) {
                        this.x = x;
                    }

                    public int getY() {
                        return y;
                    }

                    public void setY(int y) {
                        this.y = y;
                    }
                }
            }
        }
    }
}
