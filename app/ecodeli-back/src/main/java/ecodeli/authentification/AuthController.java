package ecodeli.authentification;

import ecodeli.DTO.read.RequestsServicesDTORead;
import ecodeli.DTOService.ServicesDTOServiceImpl;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.DTOService.RequestsServicesDTOServiceImpl;
import ecodeli.DTOService.UsersDTOServiceImpl;
import ecodeli.tools.ResponseErrorV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Profile("prod")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UsersDTOServiceImpl usersDTOService;
    @Autowired
    private RequestsServicesDTOServiceImpl requestsServicesDTOService;
    /*@Autowired
    private ServicesDTOServiceImpl servicesDTOService;*/

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequest request) {
        Optional<UsersDTORead> user = usersDTOService.findByEmail(request.getEmail());
        if (user.isPresent()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UsersDTORead usr = user.get();
            final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            List<RequestsServicesDTORead> requestsSvc = requestsServicesDTOService.readAllByUserReqIdUser(usr.getIdUser());
            /*Map<Integer, String> nameSvc = new HashMap<>();
            requestsSvc.stream().map(requestSvc -> {
                Integer idSvc = requestSvc.getSvc();
                nameSvc.put(idSvc, servicesDTOService.findById(re))});*/
            return ResponseEntity.ok(new AuthResponse(jwt,usr.getAccountStatus(),usr.getRole(),requestsSvc));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseErrorV2("email","User not found !"));
        }
    }
}

