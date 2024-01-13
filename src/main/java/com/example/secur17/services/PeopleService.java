package com.example.secur17.services;

import com.example.secur17.models.Person;
import com.example.secur17.repositories.PeopleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    @Transactional
    public boolean saveUser(Person user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        peopleRepository.save(user);
        return true;
    }
}








