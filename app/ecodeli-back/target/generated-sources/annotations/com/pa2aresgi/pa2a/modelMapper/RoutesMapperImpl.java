package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RoutesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RoutesDTORead;
import com.pa2aresgi.pa2a.modele.Routes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T10:08:36+0200",
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

        routes.setUser( routeDtoCreate.getUser() );
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
}
