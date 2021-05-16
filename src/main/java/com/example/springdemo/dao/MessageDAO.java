package com.example.springdemo.dao;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
public class MessageDAO {
    @NotEmpty
    private String messageBody;

    @NotEmpty
    private String mqName;

    @Id
    @Column(unique = true)
    private Long messageId;

    @CreationTimestamp
    @Column(updatable = false)
    private Date created;
}
