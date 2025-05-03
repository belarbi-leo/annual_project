package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.DepotsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.DepotsDTORead;
import com.pa2aresgi.pa2a.modele.Depots;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T20:10:08+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class DepotsMapperImpl implements DepotsMapper {

    @Override
    public DepotsDTORead toDtoRead(Depots depot) {
        if ( depot == null ) {
            return null;
        }

        DepotsDTORead depotsDTORead = new DepotsDTORead();

        depotsDTORead.setIdDepot( depot.getIdDepot() );
        depotsDTORead.setStorageCapacityDepot( depot.getStorageCapacityDepot() );
        depotsDTORead.setLocation( depot.getLocation() );
        depotsDTORead.setSuite( depot.getSuite() );
        depotsDTORead.setLocality( depot.getLocality() );
        depotsDTORead.setState( depot.getState() );
        depotsDTORead.setPostalCode( depot.getPostalCode() );
        depotsDTORead.setCountry( depot.getCountry() );
        depotsDTORead.setLatitude( depot.getLatitude() );
        depotsDTORead.setLongitude( depot.getLongitude() );

        return depotsDTORead;
    }

    @Override
    public Depots fromDtoCreate(DepotsDTOCreate depotDtoCreate) {
        if ( depotDtoCreate == null ) {
            return null;
        }

        Depots depots = new Depots();

        depots.setStorageCapacityDepot( depotDtoCreate.getStorageCapacityDepot() );
        depots.setLocation( depotDtoCreate.getLocation() );
        depots.setSuite( depotDtoCreate.getSuite() );
        depots.setLocality( depotDtoCreate.getLocality() );
        depots.setState( depotDtoCreate.getState() );
        depots.setPostalCode( depotDtoCreate.getPostalCode() );
        depots.setCountry( depotDtoCreate.getCountry() );
        depots.setLatitude( depotDtoCreate.getLatitude() );
        depots.setLongitude( depotDtoCreate.getLongitude() );

        return depots;
    }
}
