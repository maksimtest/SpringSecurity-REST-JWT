package laboratory.jwt.services;

import laboratory.jwt.entities.Role;
import laboratory.jwt.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        System.out.println("BEFORE:");
        roleRepository.findAll().forEach(r-> System.out.println("role: "+r.getName()));
        System.out.println("AFTER:");
        return roleRepository.findByName("ROLE_USER").get();
    }
}
