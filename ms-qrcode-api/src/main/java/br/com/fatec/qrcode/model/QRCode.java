package br.com.fatec.qrcode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "qrcodes")
public class QRCode extends UUIDModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String data;
    private UUID userId;
}
