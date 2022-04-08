package com.go.jpastudy.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


/**
 * @MappedSuperClass -> JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하도록 함
 * @EntityListeners(AuditingEntityLister.class) -> BaseTimeEntity 클래스에 Auditing 기능 포함
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    /**
     * 모든 Entity 상위 클래스가 되어 Entity들의 createdDate, modifiedDate 자동 관리 역할
     */

    /**
     * @CreatedDate -> Entity가 생성되어 저장될 때 시간이 자동 저장
     * @LastModifiedDate -> 조회한 Entity의 값을 변경할 때 시간이 자동 저장
     */
    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
