package lk.ucsc.sricare.serviceissuesservice.dto;

import jakarta.validation.constraints.NotNull;

public class ResolveRequestDTO {
    @NotNull
    private String status;
    private String content;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ResolveRequestDTO(@NotNull String status, String content) {
        this.status = status;
        this.content = content;
    }
}
