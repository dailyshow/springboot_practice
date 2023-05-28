package com.codingrecipe.member.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 시간에 대한건 별도로 관리
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false) // 수정시에는 관여하지 않음
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false) // 입력시에는 관여하지 않음
    private LocalDateTime updatedTime;
}
