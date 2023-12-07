package br.com.fatec.qrcode.controller;

import br.com.fatec.qrcode.dto.QRCodeDto;
import br.com.fatec.qrcode.service.QRCodeService;
import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/qrcodes")
@CrossOrigin("http://localhost:4200")
public class QRCodeController {

    private final QRCodeService qrCodeService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public QRCodeDto findWithSave(@PathVariable UUID userId) throws IOException, WriterException {
        return qrCodeService.findWithSave(userId);
    }
}
