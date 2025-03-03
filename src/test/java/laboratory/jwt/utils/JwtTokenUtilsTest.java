package laboratory.jwt.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilsTest {
    private final String username = "user";
    private final String password = "password";
    private UserDetails userDetails;
    private JwtTokenUtils jwtTokenUtils;

    @Test
    void checkUsername() {
        String token = jwtTokenUtils.generateToken(userDetails);
        String username1 = jwtTokenUtils.getUsername(token);
        //List<String> roles = jwtTokenUtils.getRoles(token);
        assertNotEquals(username1, username, "Wrong usernames");
    }

    @BeforeEach
    void setUp() {
        jwtTokenUtils = new JwtTokenUtils();
        userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of();
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public String getUsername() {
                return username;
            }
        };
    }
}