package com.example.demo.query.sp;

import java.io.Serializable;
import java.util.Date;

public class SpInputDto implements Serializable{

    private FendArtSpQueryRequest fendArtSpQueryRequest;
    private FendArtSpQueryRequestData fendArtSpQueryRequestData;

    public SpInputDto(Long requestId, String requestSystem, Date requestDate,
                      String CUPS) {
        fendArtSpQueryRequest = new FendArtSpQueryRequest(requestId, requestSystem, requestDate);
        fendArtSpQueryRequestData = new FendArtSpQueryRequestData(CUPS);
    }

    public FendArtSpQueryRequest getFendArtSpQueryRequest() {
        return fendArtSpQueryRequest;
    }

    public void setFendArtSpQueryRequest(FendArtSpQueryRequest fendArtSpQueryRequest) {
        this.fendArtSpQueryRequest = fendArtSpQueryRequest;
    }

    public FendArtSpQueryRequestData getFendArtSpQueryRequestData() {
        return fendArtSpQueryRequestData;
    }

    public void setFendArtSpQueryRequestData(FendArtSpQueryRequestData fendArtSpQueryRequestData) {
        this.fendArtSpQueryRequestData = fendArtSpQueryRequestData;
    }

    public class FendArtSpQueryRequest implements Serializable {
        private Long requestId;
        private String requestSystem;
        private Date requestDate;

        public FendArtSpQueryRequest(Long requestId, String requestSystem, Date requestDate){
            this.requestId = requestId;
            this.requestSystem = requestSystem;
            this.requestDate = requestDate;
        }

        public Long getRequestId() {
            return requestId;
        }

        public void setRequestId(Long requestId) {
            this.requestId = requestId;
        }

        public String getRequestSystem() {
            return requestSystem;
        }

        public void setRequestSystem(String requestSystem) {
            this.requestSystem = requestSystem;
        }

        public Date getRequestDate() {
            return requestDate;
        }

        public void setRequestDate(Date requestDate) {
            this.requestDate = requestDate;
        }
    }

    public class FendArtSpQueryRequestData implements Serializable {
        private String CUPS;

        public FendArtSpQueryRequestData(String CUPS){
            this.CUPS = CUPS;
        }

        public String getCUPS() {
            return CUPS;
        }

        public void setCUPS(String CUPS) {
            this.CUPS = CUPS;
        }
    }
}
