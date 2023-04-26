package com.example.bookshop.services;

import com.example.bookshop.models.Person;
import com.example.bookshop.repository.PersonRepository;
import com.example.bookshop.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByLogin(username);
        if(person.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new PersonDetails(person.get());
    }
}
