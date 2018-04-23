package com.example.demo.query.lvcsp;

import java.io.Serializable;
import java.util.Date;

public class LvcSpInputDto implements Serializable{

    private FendArtLVCSPQueryRequest fendArtLVCSPQueryRequest;
    private FendArtLVCSPQueryRequestData fendArtLVCSPQueryRequestData;

    public LvcSpInputDto(Long requestId, String requestSystem, Date requestDate,
                         String territory, int cdtidint) {
        fendArtLVCSPQueryRequest = new FendArtLVCSPQueryRequest(requestId, requestSystem, requestDate);
        fendArtLVCSPQueryRequestData = new FendArtLVCSPQueryRequestData(territory, cdtidint);
    }

    public FendArtLVCSPQueryRequest getFendArtLVCQueryRequest() {
        return fendArtLVCSPQueryRequest;
    }

    public void setFendArtLVCQueryRequest(FendArtLVCSPQueryRequest fendArtLVCQueryRequest) {
        this.fendArtLVCSPQueryRequest = fendArtLVCQueryRequest;
    }

    public FendArtLVCSPQueryRequestData getFendArtLVCQueryRequestData() {
        return fendArtLVCSPQueryRequestData;
    }

    public void setFendArtLVCQueryRequestData(FendArtLVCSPQueryRequestData fendArtLVCQueryRequestData) {
        this.fendArtLVCSPQueryRequestData = fendArtLVCQueryRequestData;
    }

    public class FendArtLVCSPQueryRequest implements Serializable {
        private Long requestId;
        private String requestSystem;
        private Date requestDate;

        public FendArtLVCSPQueryRequest(Long requestId, String requestSystem, Date requestDate){
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

    public class FendArtLVCSPQueryRequestData implements Serializable {
        private String territory;
        private int cdtidint;

        public FendArtLVCSPQueryRequestData(String territory, int cdtidint){
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
