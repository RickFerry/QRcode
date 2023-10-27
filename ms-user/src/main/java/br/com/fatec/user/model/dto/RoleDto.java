package br.com.fatec.user.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {
    String id;
    String roleName;
}
