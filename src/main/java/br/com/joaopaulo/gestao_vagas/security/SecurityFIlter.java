package br.com.joaopaulo.gestao_vagas.security;

import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFIlter extends OncePerRequestFilter {

     @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
               throws ServletException, IOException {
               
          String header = request.getHeader("Authorization");
          if(header != null){
               
          }

          filterChain.doFilter(request, response);
     }
     
}
