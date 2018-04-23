package com.example.demo.query.spresetstw;

import java.io.Serializable;
import java.util.Date;

public class SpResetStwInputDto implements Serializable{

    private FendArtSPResetSTWRequest fendArtSPResetSTWRequest;
    private FendArtSPResetSTWRequestData fendArtSPResetSTWRequestData;

    public SpResetStwInputDto(Long requestId, String requestSystem, Date requestDate,
                              String CUPS) {
        fendArtSPResetSTWRequest = new FendArtSPResetSTWRequest(requestId, requestSystem, requestDate);
        fendArtSPResetSTWRequestData = new FendArtSPResetSTWRequestData(CUPS);
    }

    public FendArtSPResetSTWRequest getFendArtSPResetSTWRequest() {
        return fendArtSPResetSTWRequest;
    }

    public void setFendArtSPResetSTWRequest(FendArtSPResetSTWRequest fendArtSPResetSTWRequest) {
        this.fendArtSPResetSTWRequest = fendArtSPResetSTWRequest;
    }

    public FendArtSPResetSTWRequestData getFendArtSPResetSTWRequestData() {
        return fendArtSPResetSTWRequestData;
    }

    public void setFendArtSPResetSTWRequestData(FendArtSPResetSTWRequestData fendArtSPResetSTWRequestData) {
        this.fendArtSPResetSTWRequestData = fendArtSPResetSTWRequestData;
    }

    public class FendArtSPResetSTWRequest implements Serializable {
        private Long requestId;
        private String requestSystem;
        private Date requestDate;

        public FendArtSPResetSTWRequest(Long requestId, String requestSystem, Date requestDate){
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

    public class FendArtSPResetSTWRequestData implements Serializable {
        private String CUPS;

        public FendArtSPResetSTWRequestData(String CUPS){
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
