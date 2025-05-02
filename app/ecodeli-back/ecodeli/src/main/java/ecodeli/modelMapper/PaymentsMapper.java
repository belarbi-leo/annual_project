package ecodeli.modelMapper;

import ecodeli.DTO.create.PaymentsDTOCreate;
import ecodeli.DTO.read.PaymentsDTORead;
import ecodeli.modele.Payments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentsMapper {
    public PaymentsDTORead toDtoRead(Payments payment);

    @Mapping(target = "ad", ignore = true)
    public Payments fromDtoCreate(PaymentsDTOCreate paymentDtoCreate);

    @Mapping(target = "ad", ignore = true)
    public Payments updatePaymentFromDtoCreate(PaymentsDTOCreate paymentDtoCreate,@MappingTarget Payments payment);
}
