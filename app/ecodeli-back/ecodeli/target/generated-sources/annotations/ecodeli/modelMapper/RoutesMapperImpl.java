package ecodeli.modelMapper;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import ecodeli.modele.Routes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-04T16:32:48+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class RoutesMapperImpl implements RoutesMapper {

    @Override
    public RoutesDTORead toDtoRead(Routes route) {
        if ( route == null ) {
            return null;
        }

        RoutesDTORead routesDTORead = new RoutesDTORead();

        routesDTORead.setIdRoute( route.getIdRoute() );
        routesDTORead.setUser( route.getUser() );
        routesDTORead.setDateCreationRoute( route.getDateCreationRoute() );
        routesDTORead.setDateStartRoute( route.getDateStartRoute() );
        routesDTORead.setLocationStartRoute( route.getLocationStartRoute() );
        routesDTORead.setSuiteStartRoute( route.getSuiteStartRoute() );
        routesDTORead.setLocalityStartRoute( route.getLocalityStartRoute() );
        routesDTORead.setStateStartRoute( route.getStateStartRoute() );
        routesDTORead.setPostalCodeStartRoute( route.getPostalCodeStartRoute() );
        routesDTORead.setCountryStartRoute( route.getCountryStartRoute() );
        routesDTORead.setLatitudeStartRoute( route.getLatitudeStartRoute() );
        routesDTORead.setLongitudeStartRoute( route.getLongitudeStartRoute() );
        routesDTORead.setDateEndRoute( route.getDateEndRoute() );
        routesDTORead.setLocationEndRoute( route.getLocationEndRoute() );
        routesDTORead.setSuiteEndRoute( route.getSuiteEndRoute() );
        routesDTORead.setLocalityEndRoute( route.getLocalityEndRoute() );
        routesDTORead.setStateEndRoute( route.getStateEndRoute() );
        routesDTORead.setPostalCodeEndRoute( route.getPostalCodeEndRoute() );
        routesDTORead.setCountryEndRoute( route.getCountryEndRoute() );
        routesDTORead.setLatitudeEndRoute( route.getLatitudeEndRoute() );
        routesDTORead.setLongitudeEndRoute( route.getLongitudeEndRoute() );
        routesDTORead.setDescriptionRoute( route.getDescriptionRoute() );
        routesDTORead.setStepRoute( route.getStepRoute() );

        return routesDTORead;
    }

    @Override
    public Routes fromDtoCreate(RoutesDTOCreate routeDtoCreate) {
        if ( routeDtoCreate == null ) {
            return null;
        }

        Routes routes = new Routes();

        routes.setDateCreationRoute( routeDtoCreate.getDateCreationRoute() );
        routes.setDateStartRoute( routeDtoCreate.getDateStartRoute() );
        routes.setLocationStartRoute( routeDtoCreate.getLocationStartRoute() );
        routes.setSuiteStartRoute( routeDtoCreate.getSuiteStartRoute() );
        routes.setLocalityStartRoute( routeDtoCreate.getLocalityStartRoute() );
        routes.setStateStartRoute( routeDtoCreate.getStateStartRoute() );
        routes.setPostalCodeStartRoute( routeDtoCreate.getPostalCodeStartRoute() );
        routes.setCountryStartRoute( routeDtoCreate.getCountryStartRoute() );
        routes.setLatitudeStartRoute( routeDtoCreate.getLatitudeStartRoute() );
        routes.setLongitudeStartRoute( routeDtoCreate.getLongitudeStartRoute() );
        routes.setDateEndRoute( routeDtoCreate.getDateEndRoute() );
        routes.setLocationEndRoute( routeDtoCreate.getLocationEndRoute() );
        routes.setSuiteEndRoute( routeDtoCreate.getSuiteEndRoute() );
        routes.setLocalityEndRoute( routeDtoCreate.getLocalityEndRoute() );
        routes.setStateEndRoute( routeDtoCreate.getStateEndRoute() );
        routes.setPostalCodeEndRoute( routeDtoCreate.getPostalCodeEndRoute() );
        routes.setCountryEndRoute( routeDtoCreate.getCountryEndRoute() );
        routes.setLatitudeEndRoute( routeDtoCreate.getLatitudeEndRoute() );
        routes.setLongitudeEndRoute( routeDtoCreate.getLongitudeEndRoute() );
        routes.setDescriptionRoute( routeDtoCreate.getDescriptionRoute() );
        routes.setStepRoute( routeDtoCreate.getStepRoute() );

        return routes;
    }

    @Override
    public Routes updateRouteFromDtoCreate(RoutesDTOCreate routeDtoCreate, Routes route) {
        if ( routeDtoCreate == null ) {
            return route;
        }

        if ( routeDtoCreate.getDateCreationRoute() != null ) {
            route.setDateCreationRoute( routeDtoCreate.getDateCreationRoute() );
        }
        if ( routeDtoCreate.getDateStartRoute() != null ) {
            route.setDateStartRoute( routeDtoCreate.getDateStartRoute() );
        }
        if ( routeDtoCreate.getLocationStartRoute() != null ) {
            route.setLocationStartRoute( routeDtoCreate.getLocationStartRoute() );
        }
        if ( routeDtoCreate.getSuiteStartRoute() != null ) {
            route.setSuiteStartRoute( routeDtoCreate.getSuiteStartRoute() );
        }
        if ( routeDtoCreate.getLocalityStartRoute() != null ) {
            route.setLocalityStartRoute( routeDtoCreate.getLocalityStartRoute() );
        }
        if ( routeDtoCreate.getStateStartRoute() != null ) {
            route.setStateStartRoute( routeDtoCreate.getStateStartRoute() );
        }
        if ( routeDtoCreate.getPostalCodeStartRoute() != null ) {
            route.setPostalCodeStartRoute( routeDtoCreate.getPostalCodeStartRoute() );
        }
        if ( routeDtoCreate.getCountryStartRoute() != null ) {
            route.setCountryStartRoute( routeDtoCreate.getCountryStartRoute() );
        }
        if ( routeDtoCreate.getLatitudeStartRoute() != null ) {
            route.setLatitudeStartRoute( routeDtoCreate.getLatitudeStartRoute() );
        }
        if ( routeDtoCreate.getLongitudeStartRoute() != null ) {
            route.setLongitudeStartRoute( routeDtoCreate.getLongitudeStartRoute() );
        }
        if ( routeDtoCreate.getDateEndRoute() != null ) {
            route.setDateEndRoute( routeDtoCreate.getDateEndRoute() );
        }
        if ( routeDtoCreate.getLocationEndRoute() != null ) {
            route.setLocationEndRoute( routeDtoCreate.getLocationEndRoute() );
        }
        if ( routeDtoCreate.getSuiteEndRoute() != null ) {
            route.setSuiteEndRoute( routeDtoCreate.getSuiteEndRoute() );
        }
        if ( routeDtoCreate.getLocalityEndRoute() != null ) {
            route.setLocalityEndRoute( routeDtoCreate.getLocalityEndRoute() );
        }
        if ( routeDtoCreate.getStateEndRoute() != null ) {
            route.setStateEndRoute( routeDtoCreate.getStateEndRoute() );
        }
        if ( routeDtoCreate.getPostalCodeEndRoute() != null ) {
            route.setPostalCodeEndRoute( routeDtoCreate.getPostalCodeEndRoute() );
        }
        if ( routeDtoCreate.getCountryEndRoute() != null ) {
            route.setCountryEndRoute( routeDtoCreate.getCountryEndRoute() );
        }
        if ( routeDtoCreate.getLatitudeEndRoute() != null ) {
            route.setLatitudeEndRoute( routeDtoCreate.getLatitudeEndRoute() );
        }
        if ( routeDtoCreate.getLongitudeEndRoute() != null ) {
            route.setLongitudeEndRoute( routeDtoCreate.getLongitudeEndRoute() );
        }
        if ( routeDtoCreate.getDescriptionRoute() != null ) {
            route.setDescriptionRoute( routeDtoCreate.getDescriptionRoute() );
        }
        if ( routeDtoCreate.getStepRoute() != null ) {
            route.setStepRoute( routeDtoCreate.getStepRoute() );
        }

        return route;
    }
}
