package peaksoft.service.impls;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.entities.Admin;
import peaksoft.entities.LoginDetails;
import peaksoft.enums.Role;
import peaksoft.repositories.AdminRepository;
import peaksoft.service.AdminService;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void saveAdmin() {
        save(
                Admin.builder()
                        .loginDetails(LoginDetails.builder()
                                .email("admin@gmail.com")
                                .password(passwordEncoder.encode("admin123"))
                                .role(Role.ADMIN)
                                .build())
                        .build()
        );
    }

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }


}
