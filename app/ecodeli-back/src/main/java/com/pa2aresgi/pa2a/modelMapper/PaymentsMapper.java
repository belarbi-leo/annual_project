package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.PaymentsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.PaymentsDTORead;
import com.pa2aresgi.pa2a.modele.Payments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentsMapper {
    public PaymentsDTORead toDtoRead(Payments payment);
    public Payments fromDtoCreate(PaymentsDTOCreate paymentDtoCreate);
}
