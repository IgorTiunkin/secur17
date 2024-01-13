package com.example.secur17.security;

import com.example.secur17.models.Person;
import com.example.secur17.services.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PeopleService peopleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleService.findByUsername(username);
        if (person.isEmpty()) throw new UsernameNotFoundException("User not found");
        return new PersonDetails(person.get());
    }
}
