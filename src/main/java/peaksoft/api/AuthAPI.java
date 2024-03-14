package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.SignInRequest;
import peaksoft.dto.responses.unions.UnionSignResponse;
import peaksoft.service.LoginDetailsService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthAPI {
    private final LoginDetailsService loginDetailsService;

    @GetMapping("/signIn")
    public UnionSignResponse signIn(@RequestBody SignInRequest signInRequest) {
        return loginDetailsService.signIn(signInRequest);
    }

}

