package umc.umc_6th.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.umc_6th.domain.FoodCategory;
import umc.umc_6th.domain.Member;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberPrefer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    // 연관관계 주인 : 참조'하는'쪽 --> 해당 값을 바꿀 수 있는 쪽
    @JoinColumn(name = "member_id") // 해당 필드를 외래키로 생성함 (+이름 설정
    private Member member ;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory category ;
}
