package ecodeli.modelMapper;

import ecodeli.DTO.create.DepotsDTOCreate;
import ecodeli.DTO.read.DepotsDTORead;
import ecodeli.modele.Depots;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
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

    @Override
    public Depots updateDepotFromDtoCreate(DepotsDTOCreate depotDtoCreate, Depots depot) {
        if ( depotDtoCreate == null ) {
            return depot;
        }

        if ( depotDtoCreate.getCountry() != null ) {
            depot.setCountry( depotDtoCreate.getCountry() );
        }
        if ( depotDtoCreate.getLatitude() != null ) {
            depot.setLatitude( depotDtoCreate.getLatitude() );
        }
        if ( depotDtoCreate.getLocality() != null ) {
            depot.setLocality( depotDtoCreate.getLocality() );
        }
        if ( depotDtoCreate.getLocation() != null ) {
            depot.setLocation( depotDtoCreate.getLocation() );
        }
        if ( depotDtoCreate.getLongitude() != null ) {
            depot.setLongitude( depotDtoCreate.getLongitude() );
        }
        if ( depotDtoCreate.getPostalCode() != null ) {
            depot.setPostalCode( depotDtoCreate.getPostalCode() );
        }
        if ( depotDtoCreate.getState() != null ) {
            depot.setState( depotDtoCreate.getState() );
        }
        if ( depotDtoCreate.getStorageCapacityDepot() != null ) {
            depot.setStorageCapacityDepot( depotDtoCreate.getStorageCapacityDepot() );
        }
        if ( depotDtoCreate.getSuite() != null ) {
            depot.setSuite( depotDtoCreate.getSuite() );
        }

        return depot;
    }
}
