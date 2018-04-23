package com.example.demo.query.lvc;

import java.io.Serializable;
import java.util.Date;

public class LvcInputDto implements Serializable{

    private FendArtLVCQueryRequest fendArtLVCQueryRequest;
    private FendArtLVCQueryRequestData fendArtLVCQueryRequestData;

    public LvcInputDto(Long requestId, String requestSystem, Date requestDate,
                       String territory, int cdtidint) {
        fendArtLVCQueryRequest = new FendArtLVCQueryRequest(requestId, requestSystem, requestDate);
        fendArtLVCQueryRequestData = new FendArtLVCQueryRequestData(territory, cdtidint);
    }

    public FendArtLVCQueryRequest getFendArtLVCQueryRequest() {
        return fendArtLVCQueryRequest;
    }

    public void setFendArtLVCQueryRequest(FendArtLVCQueryRequest fendArtLVCQueryRequest) {
        this.fendArtLVCQueryRequest = fendArtLVCQueryRequest;
    }

    public FendArtLVCQueryRequestData getFendArtLVCQueryRequestData() {
        return fendArtLVCQueryRequestData;
    }

    public void setFendArtLVCQueryRequestData(FendArtLVCQueryRequestData fendArtLVCQueryRequestData) {
        this.fendArtLVCQueryRequestData = fendArtLVCQueryRequestData;
    }

    public class FendArtLVCQueryRequest implements Serializable {
        private Long requestId;
        private String requestSystem;
        private Date requestDate;

        public FendArtLVCQueryRequest(Long requestId, String requestSystem, Date requestDate){
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

    public class FendArtLVCQueryRequestData implements Serializable {
        private String territory;
        private int cdtidint;

        public FendArtLVCQueryRequestData(String territory, int cdtidint){
            this.territory = territory;
            this.cdtidint = cdtidint;
        }

        public String getTerritory() {
            return territory;
        }

        public void setTerritory(String territory) {
            this.territory = territory;
        }

        public int getCdtidint() {
            return cdtidint;
        }

        public void setCdtidint(int cdtidint) {
            this.cdtidint = cdtidint;
        }
    }
}
