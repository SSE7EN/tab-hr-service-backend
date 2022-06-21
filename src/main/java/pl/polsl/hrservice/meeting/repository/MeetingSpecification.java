package pl.polsl.hrservice.meeting.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import pl.polsl.hrservice.meeting.domain.Meeting;
import pl.polsl.hrservice.meeting.entity.MeetingEntity;

/**
 * Created by piotrswierzy on 21.06.2022
 */
public class MeetingSpecification {

    public static Specification<MeetingEntity> candidateEmail(final String candidateEmail){
        return (root, query, builder) -> {
            if(StringUtils.isBlank(candidateEmail)) {
                return null;
            }
           return builder.equal(root.join("candidate").get("user").get("email"), candidateEmail);
        };
    }
}
