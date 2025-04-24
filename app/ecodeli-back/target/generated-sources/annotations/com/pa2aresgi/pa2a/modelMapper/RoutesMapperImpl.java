package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.RoutesDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.RoutesDTORead;
import com.pa2aresgi.pa2a.modele.Routes;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T16:32:19+0200",
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
        routes.setUser( routeDtoCreate.getUser() );

        return routes;
    }
}
