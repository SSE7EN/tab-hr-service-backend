package pl.polsl.hrservice.raport.domain;

import lombok.Builder;
import lombok.Getter;
import pl.polsl.hrservice.position.domain.Position;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by piotrswierzy on 21.06.2022
 */
@Getter
@Builder(toBuilder = true)
public class PositionInfo{
    List<Position> positions;


    public Integer positionsCount(){
        return positions.size();
    }

    public List<ProgrammingLanguageOccurrences> mostCommonProgrammingLanguages() {
        return positions.stream()
                .map(Position::programmingLanguages)
                .flatMap(Collection::stream)
                .collect(groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(programmingLanguageLongEntry -> ProgrammingLanguageOccurrences.builder()
                        .programmingLanguage(programmingLanguageLongEntry.getKey())
                        .occurrences(programmingLanguageLongEntry.getValue())
                        .build())
                .sorted((o1, o2) -> (int) (o1.occurrences() - o2.occurrences()))
                .collect(Collectors.toList());

    }
}
