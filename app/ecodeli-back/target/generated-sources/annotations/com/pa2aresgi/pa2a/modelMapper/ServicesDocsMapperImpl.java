package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.ServicesDocsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.ServicesDocsDTORead;
import com.pa2aresgi.pa2a.modele.ServicesDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class ServicesDocsMapperImpl implements ServicesDocsMapper {

    @Override
    public ServicesDocsDTORead toDtoRead(ServicesDocs serviceDoc) {
        if ( serviceDoc == null ) {
            return null;
        }

        ServicesDocsDTORead servicesDocsDTORead = new ServicesDocsDTORead();

        servicesDocsDTORead.setNameDoc( serviceDoc.getNameDoc() );
        servicesDocsDTORead.setSvc( serviceDoc.getSvc() );

        return servicesDocsDTORead;
    }

    @Override
    public ServicesDocs fromDtoCreate(ServicesDocsDTOCreate serviceDocDtoCreate) {
        if ( serviceDocDtoCreate == null ) {
            return null;
        }

        ServicesDocs servicesDocs = new ServicesDocs();

        servicesDocs.setNameDoc( serviceDocDtoCreate.getNameDoc() );
        servicesDocs.setSvc( serviceDocDtoCreate.getSvc() );

        return servicesDocs;
    }
}
