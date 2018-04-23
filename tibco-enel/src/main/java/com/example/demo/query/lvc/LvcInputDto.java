package com.example.demo.query.lvc;

import java.io.Serializable;
import java.util.Date;

public class LvcInputDto implements Serializable{

    private FendARtLVCQueryRequest fendArtLVCQueryRequest;
    private FendARtLVCQueryRequestData fendArtLVCQueryRequestData;

    public LvcInputDto(Long requestId, String requestSystem, Date requestDate,
                       String territory, int cdtidint) {
        fendArtLVCQueryRequest = new FendARtLVCQueryRequest(requestId, requestSystem, requestDate);
        fendArtLVCQueryRequestData = new FendARtLVCQueryRequestData(territory, cdtidint);
    }

    public FendARtLVCQueryRequest getFendArtLVCQueryRequest() {
        return fendArtLVCQueryRequest;
    }

    public void setFendArtLVCQueryRequest(FendARtLVCQueryRequest fendArtLVCQueryRequest) {
        this.fendArtLVCQueryRequest = fendArtLVCQueryRequest;
    }

    public FendARtLVCQueryRequestData getFendArtLVCQueryRequestData() {
        return fendArtLVCQueryRequestData;
    }

    public void setFendArtLVCQueryRequestData(FendARtLVCQueryRequestData fendArtLVCQueryRequestData) {
        this.fendArtLVCQueryRequestData = fendArtLVCQueryRequestData;
    }

    public class FendARtLVCQueryRequest implements Serializable {
        private Long requestId;
        private String requestSystem;
        private Date requestDate;

        public FendARtLVCQueryRequest(Long requestId, String requestSystem, Date requestDate){
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

    public class FendARtLVCQueryRequestData implements Serializable {
        private String territory;
        private int cdtidint;

        public FendARtLVCQueryRequestData(String territory, int cdtidint){
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
