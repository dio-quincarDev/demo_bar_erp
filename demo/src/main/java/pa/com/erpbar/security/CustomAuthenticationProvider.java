package pa.com.erpbar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import pa.com.erpbar.users.User;
import pa.com.erpbar.users.UserRepository;

import java.util.Optional;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameOrEmail = authentication.getName();
        String pin = ((UsernamePasswordAuthenticationToken) authentication).getCredentials().toString();
        User user = userRepository.findByEmail(usernameOrEmail).orElseThrow( () -> new UsernameNotFoundException(usernameOrEmail));

        if(user != null && passwordEncoder.matches(pin, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, pin, user.getAuthorities());
        }else{
            throw new BadCredentialsException("Authentication failed");
        }
    }

    @Override
    public boolean supports (Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
