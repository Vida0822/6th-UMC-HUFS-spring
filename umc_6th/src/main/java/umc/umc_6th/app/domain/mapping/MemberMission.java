package umc.umc_6th.app.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.umc_6th.app.domain.Member;
import umc.umc_6th.app.domain.Mission;
import umc.umc_6th.app.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status ;
}
