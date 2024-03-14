package peaksoft.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import peaksoft.entities.LoginDetails;
import peaksoft.exceptions.LoginDetailsNotFoundException;
import peaksoft.repositories.LoginDetailsRepository;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final LoginDetailsRepository loginDetailsRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException{

        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String bearer = "Bearer ";
        if (headerToken != null && headerToken.startsWith(bearer)) {
            String token = headerToken.substring(bearer.length());
            try {
                String email = jwtService.verifyToken(token);
                LoginDetails loginDetails = loginDetailsRepository.findByEmail(email).orElseThrow(() -> new LoginDetailsNotFoundException("Login details with email " + email + " not found!"));
                SecurityContextHolder.getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                        loginDetails.getEmail(),
                                        null,
                                        loginDetails.getAuthorities()
                                )
                        );

            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            } catch (LoginDetailsNotFoundException e) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            }

        }
        filterChain.doFilter(request, response);

    }
}
