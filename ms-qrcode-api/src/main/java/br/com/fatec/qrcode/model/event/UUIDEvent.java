package br.com.fatec.qrcode.model.event;

import br.com.fatec.qrcode.model.UUIDModel;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDEvent extends AbstractMongoEventListener<UUIDModel> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<UUIDModel> event) {
        super.onBeforeConvert(event);
        UUIDModel uuid = event.getSource();
        if (uuid.isNew()) {
            uuid.setId(UUID.randomUUID());
        }
    }
}
