
package com.plaincoded.restapi.services.jwt;

import com.plaincoded.restapi.services.userServices.MyUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;

    //FIXME put the SECTER_KEY into a file and use @Value to inject it

    private final String SECRET_KEY = "AAAAB3NzaC1yc2EAAAABJQAAAgEAm6tee3cf9ioNzCcs6o562qSd5MbwsthBnRwU06J0N+Fb/T5crnIPM45K8jsRYu5SEfEIV4iCBuA/j/JLhWs6wf6xW6flVRUEgN4eUv+oPTmUTUML6ht6+4AGbS1g3YtPovdoveKQsNGLHX5X7+leoTXpGZ5PnskHmxUr8mJ7nCPz8c5jwhWVdVh00RcnHNu+jXP5oRZr3Dztl+CrXavEDkXeF7hq1WwTetgNtCQOmTxmJR8KFlK5T19uIfB9QWFPFjBI0FWZc+RnHkuwFIEGmvguTIMdR2rSs9AFkrEzZmZeU+AlVdynaIFUJrT+jeFe8QUUsufadibJ2K/TXcC+enq74av5jrfvyfM3QrGZgf2ORKHf+S1IgLO1gpxR9wdXI+HL2SAcU2KmNXOwMoa1SGu4nHI3iPX+bBTvSblq7dEBiCjXN2rGmi2FJGzfjfR1uwRImALVozoQQ72EUl9fLVyeXgI30rGTPlxX0eNV3aYl9Mu3tJgbhCV9qUHJC5kUaYCMuht4GM5jWo5y3d1iVVqSPMeaGPavKiIR2mia5iH5Tha2kMejO7AOOJAQ+dQKtptrBYg+3u6q9EOGB5fRuEUV02W89/UHZLSL5D1f0liGZf+OSSQQX1NMTxC+c0lXrsEwW4UvkS9kFfDZp2gRcdl2FFJaSXcfBvKKEfSCdLM";

    public JwtUtil(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public ResponseEntity<UsernameAndPasswordAuthenticationResponse> getAuthenticationResponseResponseEntity(UsernameAndPasswordAuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (Exception e) {
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = generateToken(userDetails);

        return ResponseEntity.ok(new UsernameAndPasswordAuthenticationResponse(jwt));
    }
}