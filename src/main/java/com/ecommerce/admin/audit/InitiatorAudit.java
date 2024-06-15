package com.ecommerce.admin.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true)
public abstract class InitiatorAudit extends TimestampAudit{
    private static final Long serialVersionUID = 1L;

    @CreatedBy
    @Column(name = "created_by")
    private int createdBy;


    @LastModifiedBy
    @Column(name = "updated_by")
    private int updatedBy;
}
