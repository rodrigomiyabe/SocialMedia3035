package br.com.tech.socialmedia3035.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
      private String username;
      private String name;
      private String password;
      private String mail;
      private String phone;
}
