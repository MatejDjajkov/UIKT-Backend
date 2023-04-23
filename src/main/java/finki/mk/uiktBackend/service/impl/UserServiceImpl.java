package finki.mk.uiktBackend.service.impl;

import finki.mk.uiktBackend.model.Subject;
import finki.mk.uiktBackend.model.auth.Role;
import finki.mk.uiktBackend.model.auth.User;
import finki.mk.uiktBackend.model.auth.UserRoles;
import finki.mk.uiktBackend.model.dto.UserDetailsDto;
import finki.mk.uiktBackend.model.exceptions.UserNotFoundException;
import finki.mk.uiktBackend.model.helpers.UserRegisterHelper;
import finki.mk.uiktBackend.repository.RoleRepository;
import finki.mk.uiktBackend.repository.UserRepository;
import finki.mk.uiktBackend.repository.UserRoleRepository;
import finki.mk.uiktBackend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void register(String email, String password, UserRegisterHelper helper) {

        User user = new User(email, passwordEncoder.encode(password), helper.getUsername(), helper.getName(),
                helper.getSurname(), LocalDateTime.now());
        userRepository.save(user);

        UserRoles userRole = new UserRoles();
        Role role = roleRepository.findByName("ROLE_USER");
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }

    @Override
    public boolean passwordMatches(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserDetailsDto getUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = authentication.getPrincipal().toString();
        User user = userRepository.findByEmail(email);

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUsername(user.getUsername());
        userDetailsDto.setEmail(user.getEmail());
        userDetailsDto.setId(user.getId());

        List<UserRoles> roles = user.getRoles();
        List<String> userRoleNames = new ArrayList<>();
        for(UserRoles role : roles){
            userRoleNames.add(role.getRole().getName());
        }
        userDetailsDto.setRoles(userRoleNames);

        return userDetailsDto;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.findUserByEmail(email);

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (var role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void takeSubject(User user, Subject subject) {
        List<Subject> subjects=user.getFavoriteSubjects();
        if(!subjects.contains(subject)) {
            subjects.add(subject);
            user.setFavoriteSubjects(subjects);
            userRepository.save(user);
        }
    }

    @Override
    public void removeSubject(User user, Subject subject) {
        List<Subject> subjects=user.getFavoriteSubjects();
        subjects.stream().filter(s -> s.equals(subject)).findFirst().map(i -> subjects.remove(i));
        user.setFavoriteSubjects(subjects);
        userRepository.save(user);
    }
}
