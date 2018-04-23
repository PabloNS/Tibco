package com.example.demo.query.spevent;

import java.io.Serializable;
import java.util.Date;

public class SpEventInputDto implements Serializable{

    private FendArtSPEventQueryRequest fendArtSPEventQueryRequest;
    private FendArtSPEventQueryRequestData fendArtSPEventQueryRequestData;

    public SpEventInputDto(Long requestId, String requestSystem, Date requestDate,
                           String CUPS) {
        fendArtSPEventQueryRequest = new FendArtSPEventQueryRequest(requestId, requestSystem, requestDate);
        fendArtSPEventQueryRequestData = new FendArtSPEventQueryRequestData(CUPS);
    }

    public FendArtSPEventQueryRequest getFendArtSPEventQueryRequest() {
        return fendArtSPEventQueryRequest;
    }

    public void setFendArtSPEventQueryRequest(FendArtSPEventQueryRequest fendArtSPEventQueryRequest) {
        this.fendArtSPEventQueryRequest = fendArtSPEventQueryRequest;
    }

    public FendArtSPEventQueryRequestData getFendArtSPEventQueryRequestData() {
        return fendArtSPEventQueryRequestData;
    }

    public void setFendArtSPEventQueryRequestData(FendArtSPEventQueryRequestData fendArtSPEventQueryRequestData) {
        this.fendArtSPEventQueryRequestData = fendArtSPEventQueryRequestData;
    }

    public class FendArtSPEventQueryRequest implements Serializable {
        private Long requestId;
        private String requestSystem;
        private Date requestDate;

        public FendArtSPEventQueryRequest(Long requestId, String requestSystem, Date requestDate){
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

    public class FendArtSPEventQueryRequestData implements Serializable {
        private String CUPS;

        public FendArtSPEventQueryRequestData(String CUPS){
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
