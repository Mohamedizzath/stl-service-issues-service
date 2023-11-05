package lk.ucsc.sricare.serviceissuesservice.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

/*
    RequestRemark - Represents individual remark given to specific service request
 */
public class RequestRemark {
    @Id
    private String id;
    private Date created_date;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RequestRemark(String id, Date created_date, String content) {
        this.id = id;
        this.created_date = created_date;
        this.content = content;
    }
}
