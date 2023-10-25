package br.com.fatec.oauth.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class Role {

    private Long id;
    private String roleName;
}
