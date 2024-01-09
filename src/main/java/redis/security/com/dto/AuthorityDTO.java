package redis.security.com.dto;

import lombok.Data;
import redis.security.com.dao.Authority;

@Data
public class AuthorityDTO {

    private String username;
    private String authority;

    public static AuthorityDTO parseAuthorityDto (Authority authority) {
        AuthorityDTO authorityDTO = new AuthorityDTO();
        if (authority != null) {
            authorityDTO.setUsername(authority.getUsername().getUsername());
            authorityDTO.setAuthority(authority.getAuthority());
        }
        return authorityDTO;
    }
}
