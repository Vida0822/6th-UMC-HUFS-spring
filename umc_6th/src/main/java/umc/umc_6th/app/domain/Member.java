package umc.umc_6th.app.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.umc_6th.app.domain.common.BaseEntity;
import umc.umc_6th.app.domain.enums.Gender;
import umc.umc_6th.app.domain.enums.MemberStatus;
import umc.umc_6th.app.domain.enums.SocialType;
import umc.umc_6th.app.domain.mapping.MemberMission;
import umc.umc_6th.app.domain.mapping.MemberPrefer;

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
    // 기본형이 아니라 래퍼 클래스를 사용하는 이유
    //  : 기본형이면 default 값이 자동으로 설정 되기에 값의 유무 여부를 정확히 파악하기 어렵다
    //      + 래퍼 클래스면 Optional 객체 등 객체기 때문에 사용할 수 있는 다양한 부가 기능이 적용가능하다.

    private boolean locationInfo ;

    private boolean snsInfo ;

    @OneToMany(mappedBy = "member" ,
            // 연관관계 거울 (참조 당하는 쪽) -- "member" : 참조되어서 담기는 필드명
               cascade = CascadeType.ALL,
            // jpa 에서는 참조 '당하는 쪽' 클래스에서 Cascade 설정 해줘야 한다 -> 즉 Member(One)가 삭제시 이를 참조하는 Prefer (Many) 데이터들도 같이 삭제
            fetch = FetchType.LAZY)
            // 셍략가능 : @xxToMany에서는 LAZY가 디폴트.
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>() ;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>() ;

    @Transient
    // 필드 매핑 X,DB에 저장 X, 조회 X => 주로 메모리상에서만 임시로 어떤 값을 보관하고 싶을 때 사용
    private Integer calculateNum;


}
