package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.DepotsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.DepotsDTORead;
import com.pa2aresgi.pa2a.modele.Depots;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-27T21:40:51+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class DepotsMapperImpl implements DepotsMapper {

    @Override
    public DepotsDTORead toDtoRead(Depots depot) {
        if ( depot == null ) {
            return null;
        }

        DepotsDTORead depotsDTORead = new DepotsDTORead();

        depotsDTORead.setCountry( depot.getCountry() );
        depotsDTORead.setIdDepot( depot.getIdDepot() );
        depotsDTORead.setLatitude( depot.getLatitude() );
        depotsDTORead.setLocality( depot.getLocality() );
        depotsDTORead.setLocation( depot.getLocation() );
        depotsDTORead.setLongitude( depot.getLongitude() );
        depotsDTORead.setPostalCode( depot.getPostalCode() );
        depotsDTORead.setState( depot.getState() );
        depotsDTORead.setStorageCapacityDepot( depot.getStorageCapacityDepot() );
        depotsDTORead.setSuite( depot.getSuite() );

        return depotsDTORead;
    }

    @Override
    public Depots fromDtoCreate(DepotsDTOCreate depotDtoCreate) {
        if ( depotDtoCreate == null ) {
            return null;
        }

        Depots depots = new Depots();

        depots.setCountry( depotDtoCreate.getCountry() );
        depots.setLatitude( depotDtoCreate.getLatitude() );
        depots.setLocality( depotDtoCreate.getLocality() );
        depots.setLocation( depotDtoCreate.getLocation() );
        depots.setLongitude( depotDtoCreate.getLongitude() );
        depots.setPostalCode( depotDtoCreate.getPostalCode() );
        depots.setState( depotDtoCreate.getState() );
        depots.setStorageCapacityDepot( depotDtoCreate.getStorageCapacityDepot() );
        depots.setSuite( depotDtoCreate.getSuite() );

        return depots;
    }
}
