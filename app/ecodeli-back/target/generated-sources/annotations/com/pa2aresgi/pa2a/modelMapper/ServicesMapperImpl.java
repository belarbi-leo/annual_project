package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.ServicesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.ServicesDTORead;
import com.pa2aresgi.pa2a.modele.Services;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T20:10:08+0200",
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

        services.setAdminCreator( serviceDtoCreate.getAdminCreator() );
        services.setDateCreationSvc( serviceDtoCreate.getDateCreationSvc() );
        services.setNameSvc( serviceDtoCreate.getNameSvc() );
        services.setCategory( serviceDtoCreate.getCategory() );
        services.setAuth( serviceDtoCreate.getAuth() );

        return services;
    }
}
