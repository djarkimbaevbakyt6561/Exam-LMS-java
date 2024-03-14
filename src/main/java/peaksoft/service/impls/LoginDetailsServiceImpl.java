package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.requests.SignInRequest;
import peaksoft.dto.responses.SignResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionSignResponse;
import peaksoft.entities.LoginDetails;
import peaksoft.enums.Role;
import peaksoft.exceptions.LoginDetailsNotFoundException;
import peaksoft.exceptions.RoleNotFoundException;
import peaksoft.exceptions.UserNotFoundException;
import peaksoft.repositories.AdminRepository;
import peaksoft.repositories.InstructorRepository;
import peaksoft.repositories.LoginDetailsRepository;
import peaksoft.repositories.StudentRepository;
import peaksoft.service.LoginDetailsService;
@Service
@RequiredArgsConstructor
public class LoginDetailsServiceImpl implements LoginDetailsService {
    private final LoginDetailsRepository loginDetailsRepository;
    private final AdminRepository adminRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UnionSignResponse signIn(SignInRequest signInRequest) {
        try {
            LoginDetails loginDetails = loginDetailsRepository.findByEmail(signInRequest.email()).orElseThrow(() -> new RuntimeException(("User not found!")));
            String encodePassword = loginDetails.getPassword();

            if (!passwordEncoder.matches(signInRequest.password(), encodePassword))
                throw new RuntimeException("Incorrect Password");

            return UnionSignResponse.builder()
                    .data(new SignResponse(getUserIdByLoginDetails(loginDetails), loginDetails.getRole(), loginDetails.getEmail(), jwtService.createToken(loginDetails)))
                    .status(SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully authorized!").build()).build();
        } catch (RuntimeException e) {
            return UnionSignResponse.builder()
                    .data(null)
                    .status(SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build()).build();
        }

    }

    @Override
    public Long getUserIdByLoginDetails(LoginDetails loginDetails) {
        try {
            Long userId;
            switch (loginDetails.getRole()) {
                case Role.ADMIN ->
                        userId = adminRepository.findAdminByLoginDetailsId(loginDetails.getId()).orElseThrow(() -> new UserNotFoundException("Admin with login details id " + loginDetails.getId() + " not found!")).getId();
                case Role.INSTRUCTOR ->
                        userId = instructorRepository.findInstructorByLoginDetailsId(loginDetails.getId()).orElseThrow(() -> new UserNotFoundException("Instructor with login details id " + loginDetails.getId() + " not found!")).getId();
                case Role.STUDENT ->
                        userId = studentRepository.findStudentByLoginDetailsId(loginDetails.getId()).orElseThrow(() -> new UserNotFoundException("Student with login details id " + loginDetails.getId() + " not found!")).getId();
                default -> throw new RoleNotFoundException("Login details role is not found");
            }
            return userId;
        } catch (LoginDetailsNotFoundException e) {
            throw new LoginDetailsNotFoundException("Login details with id " + loginDetails.getId() + " is not found!");
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException(e.getMessage());
        } catch (RoleNotFoundException e) {
            throw new RoleNotFoundException(e.getMessage());
        }
    }
}
