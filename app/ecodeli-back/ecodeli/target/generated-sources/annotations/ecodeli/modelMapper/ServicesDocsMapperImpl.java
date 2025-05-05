package ecodeli.modelMapper;

import ecodeli.DTO.create.ServicesDocsDTOCreate;
import ecodeli.DTO.read.ServicesDocsDTORead;
import ecodeli.modele.ServicesDocs;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T16:32:47+0200",
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
