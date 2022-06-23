package pl.polsl.hrservice.position.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.hrservice.position.enumerated.ProgrammingLanguage;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

/**
 * Created by piotrswierzy on 31.03.2022
 */
@Entity
@Table(name = "positions")
@Getter
@Setter
@NoArgsConstructor
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = ProgrammingLanguage.class)
    private Set<ProgrammingLanguage> programmingLanguages;


}
