package com.avi.audit;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@EqualsAndHashCode
@ToString
@Audited
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @Column(name = "created_on", updatable = false)
    @Temporal(TIMESTAMP)
    @CreatedDate
    protected Date created_on;

    @Column(name = "updated_on")
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date updated_on;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected T created_by;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected T updated_by;

    @Version
    protected Integer vers;

}
