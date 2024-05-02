package umc.umc_6th.app.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.umc_6th.app.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region ;

    private String name ;

    private String address ;

    private int score ;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>( );

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<StoreImage> images = new ArrayList<>( );
}
