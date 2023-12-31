package br.com.fatec.qrcode.service;

import br.com.fatec.qrcode.dto.QRCodeDto;
import br.com.fatec.qrcode.mapper.QRCodeMapper;
import br.com.fatec.qrcode.model.QRCode;
import br.com.fatec.qrcode.repository.QRCodeRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@AllArgsConstructor
@Service
public class QRCodeService {

    private final QRCodeRepository qrCodeRepository;
    private final QRCodeMapper qrCodeMapper = QRCodeMapper.INSTANCE;

    @Transactional
    public QRCodeDto save(UUID userId) throws IOException, WriterException {
        final String dataText = "http://localhost:8765/ms-catalog-api/api/v1/products/".concat(userId.toString());
        final int width = 250;
        final int height = 250;
        QRCode qrCode = new QRCode();

        qrCode.setUserId(userId);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(dataText, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        qrCode.setData(Base64.getEncoder().encodeToString(pngOutputStream.toByteArray()));
        QRCode savedQrCode = qrCodeRepository.save(qrCode);
        return qrCodeMapper.toDto(savedQrCode);
    }
}
