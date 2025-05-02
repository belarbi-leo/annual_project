package ecodeli.modelMapper.subClassImpl;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import ecodeli.modelMapper.RoutesMapper;
import ecodeli.modele.Routes;
import ecodeli.modele.Users;
import ecodeli.repository.UsersRepository;
import ecodeli.tools.ResponseErrorV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoutesMapperSubClassImpl {
    private final RoutesMapper routesMapper;
    private final UsersRepository usersRepository;

    public RoutesDTORead toDtoRead(Routes route){
        return routesMapper.toDtoRead(route);
    }

    public Object fromDtoCreate(RoutesDTOCreate routeDtoCreate){
        Routes route = routesMapper.fromDtoCreate(routeDtoCreate);
        Optional<Users> user = usersRepository.findById(routeDtoCreate.getUser());
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if(user.isEmpty()) retourNeg.add("user", "Id user for this route not found !");
        else route.setUser(user.get());
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return route;
    }

    public Object updateRouteFromDtoCreate(RoutesDTOCreate routeDtoCreate, Routes route){
        Routes rt = routesMapper.updateRouteFromDtoCreate(routeDtoCreate,route);
        ResponseErrorV2 retourNeg = new ResponseErrorV2();
        if (routeDtoCreate.getUser() != null){
            Optional<Users> user = usersRepository.findById(routeDtoCreate.getUser());
            if (user.isEmpty()) retourNeg.add("user", "Id user for this route not found !");
            else rt.setUser(user.get());
        }
        if (!retourNeg.getErrors().isEmpty())
            return retourNeg;
        else
            return rt;
    }
}
