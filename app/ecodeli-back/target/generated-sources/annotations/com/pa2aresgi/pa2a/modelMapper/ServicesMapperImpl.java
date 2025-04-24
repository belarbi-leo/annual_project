package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.ServicesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.ServicesDTORead;
import com.pa2aresgi.pa2a.modele.Services;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
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

        services.setAdminCreator( serviceDtoCreate.getAdminCreator() );
        services.setAuth( serviceDtoCreate.getAuth() );
        services.setCategory( serviceDtoCreate.getCategory() );
        services.setDateCreationSvc( serviceDtoCreate.getDateCreationSvc() );
        services.setNameSvc( serviceDtoCreate.getNameSvc() );

        return services;
    }
}
