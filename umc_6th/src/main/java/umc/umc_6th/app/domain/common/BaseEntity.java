package umc.umc_6th.app.domain.common;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 부모 클래스가 가지는 칼럼만 매핑 정보로 제공
@EntityListeners(AuditingEntityListener.class)
// 엔티티가 생성되고, 변경되는 그 시점을 감지하여 생성시각, 수정시각, 생성한 사람, 수정한 사람을 기록
@Getter
public class BaseEntity {

    @CreatedDate // Entity가 생성되어 저장(DB)될 때 시간이 자동 저장
    private LocalDateTime createdAt ;
    // LocalDateTime : 데이터베이스의 datetime(6)과 매핑
    // LocalDate : 데이터베이스의 date 타입과 매핑
    // LocalTime : 데이터베이스의 time 타입과 매핑

    @LastModifiedDate // : 조회한 Entity의 값을 변경(DB)할 때 시간이 자동 저장
    private LocalDateTime updatedAt;
}
