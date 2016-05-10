package ru.kpfu.itis.security;

/**
 * Created by 1 on 13.05.2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.ArrayList;
import ru.kpfu.itis.repository.MySQLUserService;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.repository.UserService;

import java.util.List;

public class AuthProvider implements AuthenticationProvider {

    @Autowired
    UserService userRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();

        User user = userRepository.loadUserByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        String password = authentication.getCredentials().toString();
        if (password == user.getPassword()) {
            throw new BadCredentialsException("invalid password");
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
