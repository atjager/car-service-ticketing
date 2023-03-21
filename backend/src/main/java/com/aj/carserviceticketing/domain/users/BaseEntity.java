package com.aj.carserviceticketing.domain.users;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
abstract class BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    protected Date updated;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
        System.out.println("--------------------------------------------HALOooooooooo");
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
