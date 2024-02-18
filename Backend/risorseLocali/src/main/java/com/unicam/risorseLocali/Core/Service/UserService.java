package com.unicam.risorseLocali.Core.Service;

import com.unicam.risorseLocali.Core.Repository.UserRepository;
import com.unicam.risorseLocali.Security.User.UserSecurity;
import com.unicam.risorseLocali.Core.Model.Entity.User;
import com.unicam.risorseLocali.Util.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * Questa classe ha il compito di fornire i servizi
 * relativi agli account degli utenti.
 */
@Service("userService")
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        return this.userRepository.findById(id);
    }

    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    public void deleteUser(String id) {
        this.userRepository.deleteById(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserSecurity(user.get());
    }

    public UserDTO convertToDTO(Optional<User> user){
        return new UserDTO(user.get().getNomeUtente(), user.get().getEmail(), user.get().getPassword(),
                user.get().getAnagrafica(), user.get().getRuolo().name());
    }

}