package ecodeli.modelMapper;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import ecodeli.modele.Routes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class RoutesMapperImpl implements RoutesMapper {

    @Override
    public RoutesDTORead toDtoRead(Routes route) {
        if ( route == null ) {
            return null;
        }

        RoutesDTORead routesDTORead = new RoutesDTORead();

        routesDTORead.setCountryEndRoute( route.getCountryEndRoute() );
        routesDTORead.setCountryStartRoute( route.getCountryStartRoute() );
        routesDTORead.setDateCreationRoute( route.getDateCreationRoute() );
        routesDTORead.setDateEndRoute( route.getDateEndRoute() );
        routesDTORead.setDateStartRoute( route.getDateStartRoute() );
        routesDTORead.setDescriptionRoute( route.getDescriptionRoute() );
        routesDTORead.setIdRoute( route.getIdRoute() );
        routesDTORead.setLatitudeEndRoute( route.getLatitudeEndRoute() );
        routesDTORead.setLatitudeStartRoute( route.getLatitudeStartRoute() );
        routesDTORead.setLocalityEndRoute( route.getLocalityEndRoute() );
        routesDTORead.setLocalityStartRoute( route.getLocalityStartRoute() );
        routesDTORead.setLocationEndRoute( route.getLocationEndRoute() );
        routesDTORead.setLocationStartRoute( route.getLocationStartRoute() );
        routesDTORead.setLongitudeEndRoute( route.getLongitudeEndRoute() );
        routesDTORead.setLongitudeStartRoute( route.getLongitudeStartRoute() );
        routesDTORead.setPostalCodeEndRoute( route.getPostalCodeEndRoute() );
        routesDTORead.setPostalCodeStartRoute( route.getPostalCodeStartRoute() );
        routesDTORead.setStateEndRoute( route.getStateEndRoute() );
        routesDTORead.setStateStartRoute( route.getStateStartRoute() );
        routesDTORead.setStepRoute( route.getStepRoute() );
        routesDTORead.setSuiteEndRoute( route.getSuiteEndRoute() );
        routesDTORead.setSuiteStartRoute( route.getSuiteStartRoute() );
        routesDTORead.setUser( route.getUser() );

        return routesDTORead;
    }

    @Override
    public Routes fromDtoCreate(RoutesDTOCreate routeDtoCreate) {
        if ( routeDtoCreate == null ) {
            return null;
        }

        Routes routes = new Routes();

        routes.setCountryEndRoute( routeDtoCreate.getCountryEndRoute() );
        routes.setCountryStartRoute( routeDtoCreate.getCountryStartRoute() );
        routes.setDateCreationRoute( routeDtoCreate.getDateCreationRoute() );
        routes.setDateEndRoute( routeDtoCreate.getDateEndRoute() );
        routes.setDateStartRoute( routeDtoCreate.getDateStartRoute() );
        routes.setDescriptionRoute( routeDtoCreate.getDescriptionRoute() );
        routes.setLatitudeEndRoute( routeDtoCreate.getLatitudeEndRoute() );
        routes.setLatitudeStartRoute( routeDtoCreate.getLatitudeStartRoute() );
        routes.setLocalityEndRoute( routeDtoCreate.getLocalityEndRoute() );
        routes.setLocalityStartRoute( routeDtoCreate.getLocalityStartRoute() );
        routes.setLocationEndRoute( routeDtoCreate.getLocationEndRoute() );
        routes.setLocationStartRoute( routeDtoCreate.getLocationStartRoute() );
        routes.setLongitudeEndRoute( routeDtoCreate.getLongitudeEndRoute() );
        routes.setLongitudeStartRoute( routeDtoCreate.getLongitudeStartRoute() );
        routes.setPostalCodeEndRoute( routeDtoCreate.getPostalCodeEndRoute() );
        routes.setPostalCodeStartRoute( routeDtoCreate.getPostalCodeStartRoute() );
        routes.setStateEndRoute( routeDtoCreate.getStateEndRoute() );
        routes.setStateStartRoute( routeDtoCreate.getStateStartRoute() );
        routes.setStepRoute( routeDtoCreate.getStepRoute() );
        routes.setSuiteEndRoute( routeDtoCreate.getSuiteEndRoute() );
        routes.setSuiteStartRoute( routeDtoCreate.getSuiteStartRoute() );

        return routes;
    }

    @Override
    public Routes updateRouteFromDtoCreate(RoutesDTOCreate routeDtoCreate, Routes route) {
        if ( routeDtoCreate == null ) {
            return route;
        }

        if ( routeDtoCreate.getCountryEndRoute() != null ) {
            route.setCountryEndRoute( routeDtoCreate.getCountryEndRoute() );
        }
        if ( routeDtoCreate.getCountryStartRoute() != null ) {
            route.setCountryStartRoute( routeDtoCreate.getCountryStartRoute() );
        }
        if ( routeDtoCreate.getDateCreationRoute() != null ) {
            route.setDateCreationRoute( routeDtoCreate.getDateCreationRoute() );
        }
        if ( routeDtoCreate.getDateEndRoute() != null ) {
            route.setDateEndRoute( routeDtoCreate.getDateEndRoute() );
        }
        if ( routeDtoCreate.getDateStartRoute() != null ) {
            route.setDateStartRoute( routeDtoCreate.getDateStartRoute() );
        }
        if ( routeDtoCreate.getDescriptionRoute() != null ) {
            route.setDescriptionRoute( routeDtoCreate.getDescriptionRoute() );
        }
        if ( routeDtoCreate.getLatitudeEndRoute() != null ) {
            route.setLatitudeEndRoute( routeDtoCreate.getLatitudeEndRoute() );
        }
        if ( routeDtoCreate.getLatitudeStartRoute() != null ) {
            route.setLatitudeStartRoute( routeDtoCreate.getLatitudeStartRoute() );
        }
        if ( routeDtoCreate.getLocalityEndRoute() != null ) {
            route.setLocalityEndRoute( routeDtoCreate.getLocalityEndRoute() );
        }
        if ( routeDtoCreate.getLocalityStartRoute() != null ) {
            route.setLocalityStartRoute( routeDtoCreate.getLocalityStartRoute() );
        }
        if ( routeDtoCreate.getLocationEndRoute() != null ) {
            route.setLocationEndRoute( routeDtoCreate.getLocationEndRoute() );
        }
        if ( routeDtoCreate.getLocationStartRoute() != null ) {
            route.setLocationStartRoute( routeDtoCreate.getLocationStartRoute() );
        }
        if ( routeDtoCreate.getLongitudeEndRoute() != null ) {
            route.setLongitudeEndRoute( routeDtoCreate.getLongitudeEndRoute() );
        }
        if ( routeDtoCreate.getLongitudeStartRoute() != null ) {
            route.setLongitudeStartRoute( routeDtoCreate.getLongitudeStartRoute() );
        }
        if ( routeDtoCreate.getPostalCodeEndRoute() != null ) {
            route.setPostalCodeEndRoute( routeDtoCreate.getPostalCodeEndRoute() );
        }
        if ( routeDtoCreate.getPostalCodeStartRoute() != null ) {
            route.setPostalCodeStartRoute( routeDtoCreate.getPostalCodeStartRoute() );
        }
        if ( routeDtoCreate.getStateEndRoute() != null ) {
            route.setStateEndRoute( routeDtoCreate.getStateEndRoute() );
        }
        if ( routeDtoCreate.getStateStartRoute() != null ) {
            route.setStateStartRoute( routeDtoCreate.getStateStartRoute() );
        }
        if ( routeDtoCreate.getStepRoute() != null ) {
            route.setStepRoute( routeDtoCreate.getStepRoute() );
        }
        if ( routeDtoCreate.getSuiteEndRoute() != null ) {
            route.setSuiteEndRoute( routeDtoCreate.getSuiteEndRoute() );
        }
        if ( routeDtoCreate.getSuiteStartRoute() != null ) {
            route.setSuiteStartRoute( routeDtoCreate.getSuiteStartRoute() );
        }

        return route;
    }
}
