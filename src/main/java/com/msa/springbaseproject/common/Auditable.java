package com.msa.springbaseproject.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Auditable {

    private Date createdDate;
    private Long createdBy;
    private Date updatedDate;
    private Long updatedBy;

    @Version
    private Integer version;

    @JsonIgnore
    private boolean deleted = false;

    @PreRemove
    public void preRemove() {
        if (removable()) return;
    }

    protected boolean removable() {
        return false;
    }

    // No argument for prePersist
    @PrePersist
    void prePersist() {
        Utilities.getLoginUser().ifPresent(user -> this.setCreatedBy(user.getUserId()));
        this.setCreatedDate(new Date());
        this.setVersion(1);
    }

    // No argument for preUpdate
    @PreUpdate
    void preUpdate() {
        Utilities.getLoginUser().ifPresent(user -> this.setUpdatedBy(user.getUserId()));
        this.setUpdatedDate(new Date());
        this.setVersion((this.getVersion() == null ? 1 : this.getVersion()) + 1);
    }
}
