package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.ServicesDocsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.ServicesDocsDTORead;
import com.pa2aresgi.pa2a.modele.ServicesDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T23:10:07+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class ServicesDocsMapperImpl implements ServicesDocsMapper {

    @Override
    public ServicesDocsDTORead toDtoRead(ServicesDocs serviceDoc) {
        if ( serviceDoc == null ) {
            return null;
        }

        ServicesDocsDTORead servicesDocsDTORead = new ServicesDocsDTORead();

        servicesDocsDTORead.setSvc( serviceDoc.getSvc() );
        servicesDocsDTORead.setNameDoc( serviceDoc.getNameDoc() );

        return servicesDocsDTORead;
    }

    @Override
    public ServicesDocs fromDtoCreate(ServicesDocsDTOCreate serviceDocDtoCreate) {
        if ( serviceDocDtoCreate == null ) {
            return null;
        }

        ServicesDocs servicesDocs = new ServicesDocs();

        servicesDocs.setSvc( serviceDocDtoCreate.getSvc() );
        servicesDocs.setNameDoc( serviceDocDtoCreate.getNameDoc() );

        return servicesDocs;
    }
}
