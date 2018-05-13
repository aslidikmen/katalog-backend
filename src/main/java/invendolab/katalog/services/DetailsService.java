package invendolab.katalog.services;

import invendolab.katalog.models.Consumer;
import invendolab.katalog.repositories.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {

    @Autowired
    ConsumerRepository consumers;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Consumer consumer = consumers.findByEmail(email);
        if (consumer == null){
            throw new UsernameNotFoundException(email + " was not found");
        }
        return new org.springframework.security.core.userdetails.User(
                consumer.getEmail(),
                consumer.getPassword(),
                AuthorityUtils.createAuthorityList(consumer.getRoles())
        );
    }
}