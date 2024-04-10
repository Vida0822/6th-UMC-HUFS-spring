package umc.umc_6th.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.umc_6th.domain.common.BaseEntity;
import umc.umc_6th.domain.enums.Gender;
import umc.umc_6th.domain.enums.MemberStatus;
import umc.umc_6th.domain.enums.SocialType;
import umc.umc_6th.domain.mapping.MemberMission;
import umc.umc_6th.domain.mapping.MemberPrefer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스가 JPA의 엔티티임
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20) // VARCHAR(20) - null 허용 x
    // 칼럼별 세부적인 설정은 @Column으로 해준다
    private String name ;

    @Enumerated(EnumType.STRING)
    // enum 을 엔티티에 적용
    // Original : enum의 순서가 저장 --> enum 순서 바뀌면 에러
    private Gender gender ;

    private int age ;

    @Column(nullable = false, length = 40)
    private String address ;

    @Column(nullable = false, length = 40)
    private String spec_address ;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    private LocalDateTime inactive_Date ;

    @Enumerated(EnumType.STRING)
    private SocialType social_type;

    @Column(nullable = false, length = 50)
    private String email;

    private Integer point;

    private boolean locationInfo ;

    private boolean snsInfo ;

    @OneToMany(mappedBy = "member" ,
            // 연관관계 거울 (참조 당하는 쪽) -- "member" : 참조되어서 담기는 필드명
               cascade = CascadeType.ALL)
            // jpa 에서는 참조 '당하는 쪽'에서 Cascade 설정 해줘야 한다 -> 즉 Member(One)가 삭제시 이를 참조하는 Prefer (Many) 데이터들도 같이 삭제
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>() ;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>() ;



}
