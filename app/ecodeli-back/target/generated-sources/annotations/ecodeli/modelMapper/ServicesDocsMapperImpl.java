package ecodeli.modelMapper;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import ecodeli.modele.ServicesDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

        return servicesDocs;
    }

    @Override
    public ServicesDocs updateServiceDocfromDtoCreate(ServicesDocsDTOCreate serviceDocDTOCreate, ServicesDocs serviceDoc) {
        if ( serviceDocDTOCreate == null ) {
            return serviceDoc;
        }

        if ( serviceDocDTOCreate.getNameDoc() != null ) {
            serviceDoc.setNameDoc( serviceDocDTOCreate.getNameDoc() );
        }

        return serviceDoc;
    }
}
