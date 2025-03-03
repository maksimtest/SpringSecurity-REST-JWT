package laboratory.jwt.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenUtils {
    @Value("${token.signing.key}")
    //private String jwtSigningKey="53A73E5F1C4E0A2D3B5F2D784E6A1B423D6F247D1F6E5C3A596D635A75327855";
    private String jwtSigningKey;

    @Value("${jwt.lifetime}")
    private Duration jwtLifetime;
    //private Duration jwtLifetime=Duration.ofMinutes(10);

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);

        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() + jwtLifetime.toMillis());
        return Jwts.builder()
                .claims(claims)
                //.setClaims(claims)
                .subject(userDetails.getUsername())
                //.setSubject(userDetails.getUsername())
                .issuedAt(issuedDate)
                //.setIssuedAt(issuedDate)
                .expiration(expiredDate)
                //.setExpiration(expiredDate)
                .signWith(getSigningKey())
                //.signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public String getUsername(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get("roles", List.class);
    }

    private Claims getAllClaimsFromToken(String token) {
        System.out.println("getAllClaimsFromToken(sop): {"+token+"}");
        log.debug("getAllClaimsFromToken: {}", token);
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

//        return Jwts.builder()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
