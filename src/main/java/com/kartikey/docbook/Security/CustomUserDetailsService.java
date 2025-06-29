package com.kartikey.docbook.Security;

import com.kartikey.docbook.model.User;
import com.kartikey.docbook.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user=userRepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        return new CustomUserDetails(user);
    }
}
