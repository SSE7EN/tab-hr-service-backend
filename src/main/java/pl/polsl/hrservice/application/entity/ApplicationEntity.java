package pl.polsl.hrservice.application.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.hrservice.candidate.entity.CandidateEntity;
import pl.polsl.hrservice.document.entity.DocumentEntity;
import pl.polsl.hrservice.position.entity.PositionEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by piotrswierzy on 31.03.2022
 */
@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String description;

    @OneToMany(mappedBy = "application")
    private List<DocumentEntity> documents;

    @ManyToOne
    private PositionEntity position;

    @ManyToOne
    private CandidateEntity candidate;

}
