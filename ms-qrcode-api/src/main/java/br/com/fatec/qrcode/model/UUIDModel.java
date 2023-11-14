package br.com.fatec.qrcode.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Getter
@Setter
public class UUIDModel implements Persistable<UUID> {

    @Id
    protected UUID id;

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
