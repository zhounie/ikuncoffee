package com.ikuncoffee.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikuncoffee.dto.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		Object marker = request.getAttribute("auth_error");
		ApiResponse<Void> resp = new ApiResponse<>();
		if (marker != null && "TOKEN_EXPIRED".equals(marker.toString())) {
			resp.setCode(40101);
			resp.setMessage("登录已过期，请重新登录");
		} else if (marker != null && "TOKEN_INVALID".equals(marker.toString())) {
			resp.setCode(40102);
			resp.setMessage("令牌无效");
		} else {
			resp.setCode(401);
			resp.setMessage("未认证或令牌缺失");
		}
		response.getWriter().write(objectMapper.writeValueAsString(resp));
	}
}


