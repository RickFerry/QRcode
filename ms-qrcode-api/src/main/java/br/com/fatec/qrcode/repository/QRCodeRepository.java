package br.com.fatec.qrcode.repository;

import br.com.fatec.qrcode.model.QRCode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QRCodeRepository extends MongoRepository<QRCode, UUID> {
}
