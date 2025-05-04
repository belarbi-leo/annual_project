package ecodeli.modelMapper;

import ecodeli.DTO.create.ServicesDTOCreate;
import ecodeli.DTO.read.ServicesDTORead;
import ecodeli.modele.Services;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T00:51:10+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class ServicesMapperImpl implements ServicesMapper {

    @Override
    public ServicesDTORead toDtoRead(Services service) {
        if ( service == null ) {
            return null;
        }

        ServicesDTORead servicesDTORead = new ServicesDTORead();

        servicesDTORead.setIdSvc( service.getIdSvc() );
        servicesDTORead.setAdminCreator( service.getAdminCreator() );
        servicesDTORead.setDateCreationSvc( service.getDateCreationSvc() );
        servicesDTORead.setNameSvc( service.getNameSvc() );
        servicesDTORead.setCategory( service.getCategory() );
        servicesDTORead.setAuth( service.getAuth() );

        return servicesDTORead;
    }

    @Override
    public Services fromDtoCreate(ServicesDTOCreate serviceDtoCreate) {
        if ( serviceDtoCreate == null ) {
            return null;
        }

        Services services = new Services();

        services.setDateCreationSvc( serviceDtoCreate.getDateCreationSvc() );
        services.setNameSvc( serviceDtoCreate.getNameSvc() );
        services.setCategory( serviceDtoCreate.getCategory() );
        services.setAuth( serviceDtoCreate.getAuth() );

        return services;
    }

    @Override
    public Services updateServiceFromDtoCreate(ServicesDTOCreate serviceDtoCreate, Services service) {
        if ( serviceDtoCreate == null ) {
            return service;
        }

        if ( serviceDtoCreate.getDateCreationSvc() != null ) {
            service.setDateCreationSvc( serviceDtoCreate.getDateCreationSvc() );
        }
        if ( serviceDtoCreate.getNameSvc() != null ) {
            service.setNameSvc( serviceDtoCreate.getNameSvc() );
        }
        if ( serviceDtoCreate.getCategory() != null ) {
            service.setCategory( serviceDtoCreate.getCategory() );
        }
        if ( serviceDtoCreate.getAuth() != null ) {
            service.setAuth( serviceDtoCreate.getAuth() );
        }

        return service;
    }
}
