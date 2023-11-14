package br.com.fatec.qrcode.mapper;

import br.com.fatec.qrcode.dto.QRCodeDto;
import br.com.fatec.qrcode.model.QRCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QRCodeMapper {

    QRCodeMapper INSTANCE = Mappers.getMapper(QRCodeMapper.class);

    QRCode toModel(QRCodeDto qrCodeDto);
    QRCodeDto toDto(QRCode qrCode);
}
