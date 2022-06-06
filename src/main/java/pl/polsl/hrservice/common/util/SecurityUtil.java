package pl.polsl.hrservice.common.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import pl.polsl.hrservice.user.enumerator.Role;

@UtilityClass
public class SecurityUtil {

    public static String getCurrentUserMainEmail() {
        final var auth = SecurityContextHolder.getContext().getAuthentication();
        final var principal = auth != null ? auth.getPrincipal() : null;
        if (principal != null) {
            if (principal instanceof User) {
                return ((User) principal).getUsername();
            } else if (principal instanceof String) {
                return (String) principal;
            }
        }

        return null;
    }

    public static Role getCurrentUserRole(){
        final var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null) {
            String role = auth.getAuthorities().toArray()[0].toString();
            if (role.equals("ROLE_ADMIN")) {
                return Role.ADMIN;
            }
        }
        return null;
    }

}
