package pl.polsl.hrservice.candidate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.hrservice.application.entity.ApplicationEntity;
import pl.polsl.hrservice.position.entity.PositionEntity;
import pl.polsl.hrservice.user.entity.UserEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by piotrswierzy on 31.03.2022
 */
@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "candidate")
    private List<ApplicationEntity> applications;
    @OneToOne(cascade = CascadeType.REMOVE)
    private UserEntity user;

}
