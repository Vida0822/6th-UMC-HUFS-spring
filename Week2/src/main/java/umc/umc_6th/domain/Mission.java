package umc.umc_6th.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.umc_6th.domain.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store ;

    private int reward;

    private LocalDateTime deadline ;

    private String spec ;
}
