package ecodeli.modelMapper;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.modele.Services;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ServicesMapperImpl implements ServicesMapper {

    @Override
    public ServicesDTORead toDtoRead(Services service) {
        if ( service == null ) {
            return null;
        }

        ServicesDTORead servicesDTORead = new ServicesDTORead();

        servicesDTORead.setAdminCreator( service.getAdminCreator() );
        servicesDTORead.setAuth( service.getAuth() );
        servicesDTORead.setCategory( service.getCategory() );
        servicesDTORead.setDateCreationSvc( service.getDateCreationSvc() );
        servicesDTORead.setIdSvc( service.getIdSvc() );
        servicesDTORead.setNameSvc( service.getNameSvc() );

        return servicesDTORead;
    }

    @Override
    public Services fromDtoCreate(ServicesDTOCreate serviceDtoCreate) {
        if ( serviceDtoCreate == null ) {
            return null;
        }

        Services services = new Services();

        services.setAuth( serviceDtoCreate.getAuth() );
        services.setCategory( serviceDtoCreate.getCategory() );
        services.setDateCreationSvc( serviceDtoCreate.getDateCreationSvc() );
        services.setNameSvc( serviceDtoCreate.getNameSvc() );

        return services;
    }

    @Override
    public Services updateServiceFromDtoCreate(ServicesDTOCreate serviceDtoCreate, Services service) {
        if ( serviceDtoCreate == null ) {
            return service;
        }

        if ( serviceDtoCreate.getAuth() != null ) {
            service.setAuth( serviceDtoCreate.getAuth() );
        }
        if ( serviceDtoCreate.getCategory() != null ) {
            service.setCategory( serviceDtoCreate.getCategory() );
        }
        if ( serviceDtoCreate.getDateCreationSvc() != null ) {
            service.setDateCreationSvc( serviceDtoCreate.getDateCreationSvc() );
        }
        if ( serviceDtoCreate.getNameSvc() != null ) {
            service.setNameSvc( serviceDtoCreate.getNameSvc() );
        }

        return service;
    }
}
