package lk.ucsc.sricare.serviceissuesservice.controller;

import jakarta.validation.Valid;
import lk.ucsc.sricare.serviceissuesservice.dto.PostRequestDTO;
import lk.ucsc.sricare.serviceissuesservice.dto.ResolveRequestDTO;
import lk.ucsc.sricare.serviceissuesservice.entity.RequestRemark;
import lk.ucsc.sricare.serviceissuesservice.entity.RequestStatus;
import lk.ucsc.sricare.serviceissuesservice.entity.ServiceRequest;
import lk.ucsc.sricare.serviceissuesservice.service.ServiceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/*
    ServiceController - exposes endpoints at [domain-name]/resolve-request
        Available endpoints

            /resolve-request [GET] - Return all the requests which are type REPORT
            /report-request?userId=[userId] [GET] - Return all the request(type-REPORT) which associated with that userId
            /report-request?id=[id] [GET] - Return the request associated with that id

 */
@RestController
public class ResolveServiceController {
    ServiceRequestService serviceRequestService;

    public ResolveServiceController(ServiceRequestService serviceRequestService){
        this.serviceRequestService = serviceRequestService;
    }
    @PostMapping("/resolve-request/{id}")
    public ServiceRequest createRequest(@PathVariable String id, @Valid @RequestBody ResolveRequestDTO body){
        // Fetching service request from the db using id
        ServiceRequest request = serviceRequestService.getById(id);

        if(request == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report request not found");
        } else {
            RequestStatus status = RequestStatus.PENDING;

            if(Objects.equals(body.getStatus(), "PENDING")){
                status = RequestStatus.PENDING;
            } else if(Objects.equals(body.getStatus(), "PROCESSING")){
                status = RequestStatus.PROCESSING;
            } else if(Objects.equals(body.getStatus(), "RESOLVED")){
                status = RequestStatus.RESOLVED;
            } else if(Objects.equals(body.getStatus(), "REJECTED")){
                status = RequestStatus.REJECTED;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request status");
            }

            if(body.getContent() != null){
                UUID remarkUUID = UUID.randomUUID();
                RequestRemark remark = new RequestRemark(remarkUUID.toString(), new Date(), body.getContent());

                return serviceRequestService.updateRequestStatus(id, status, remark);
            } else {
                return serviceRequestService.updateRequestStatus(id, status, null);
            }
        }
    }
}
