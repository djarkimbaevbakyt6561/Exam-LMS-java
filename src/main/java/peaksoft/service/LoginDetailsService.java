package peaksoft.service;

import peaksoft.dto.requests.SignInRequest;
import peaksoft.dto.responses.unions.UnionSignResponse;
import peaksoft.entities.LoginDetails;

public interface LoginDetailsService {
    UnionSignResponse signIn(SignInRequest signInRequest);
    Long getUserIdByLoginDetails(LoginDetails loginDetails);
}
