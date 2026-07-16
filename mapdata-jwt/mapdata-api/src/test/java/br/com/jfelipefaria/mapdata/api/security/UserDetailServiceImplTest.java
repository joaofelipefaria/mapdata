package br.com.jfelipefaria.mapdata.api.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserDetailServiceImplTest {

    @Test
    void shouldLoadAdminUserWithEncodedPassword() throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDetailServiceImpl service = new UserDetailServiceImpl(encoder);

        UserDetails user = service.loadUserByUsername("admin");

        assertEquals("admin", user.getUsername());
        assertTrue(encoder.matches("admin123", user.getPassword()));
        assertTrue(user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }
}
