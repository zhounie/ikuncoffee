package com.ikuncoffee.controller;

import com.ikuncoffee.dto.request.LoginRequest;
import com.ikuncoffee.dto.response.ApiResponse;
import com.ikuncoffee.dto.response.LoginResponse;
import com.ikuncoffee.dto.response.UserResponse;
import com.ikuncoffee.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;


	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login (@Valid @RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        LoginResponse result = new LoginResponse();
        result.setToken(token);
        result.setUsername(userDetails.getUsername());
        return ApiResponse.success(result);
    }

	// 临时工具接口：生成 BCrypt 加密串，使用后可删除
	@GetMapping("/hash")
	public ApiResponse<Map<String, String>> hash(@RequestParam("raw") String raw) {
		String encoded = passwordEncoder.encode(raw);
		Map<String, String> resp = new HashMap<>();
		resp.put("raw", raw);
		resp.put("encoded", encoded);
		return ApiResponse.success(resp);
	}

    @GetMapping("/userInfo")
    public ApiResponse<UserResponse> getUserInfo (Authentication authentication) {
        return ApiResponse.success(new UserResponse());
    }

}
