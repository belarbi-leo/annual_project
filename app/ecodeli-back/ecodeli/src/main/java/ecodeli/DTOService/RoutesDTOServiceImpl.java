package ecodeli.DTOService;

import ecodeli.DTO.create.RoutesDTOCreate;
import ecodeli.DTO.read.RoutesDTORead;
import ecodeli.modelMapper.subClassImpl.RoutesMapperSubClassImpl;
import ecodeli.modele.Routes;
import ecodeli.repository.RoutesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoutesDTOServiceImpl implements RoutesDTOService {
    private RoutesRepository routesRepository;
    @Autowired
    private RoutesMapperSubClassImpl routesMapper;

    @Override
    public Object create(RoutesDTOCreate routeDtoCreate){
        if (routeDtoCreate.getDateCreationRoute() == null) routeDtoCreate.setDateCreationRoute(Timestamp.valueOf(LocalDateTime.now()));
        if (routeDtoCreate.getStepRoute() == null) routeDtoCreate.setStepRoute(0);

        Object obj = routesMapper.fromDtoCreate(routeDtoCreate);
        if (obj instanceof Routes)
            return routesMapper.toDtoRead(routesRepository.save((Routes) obj));
        else
            return obj;
    }

    @Override
    public List<RoutesDTORead> readAll(){
        return routesRepository.findAll().stream().map(routesMapper::toDtoRead).toList();
    }

    @Override
    public List<RoutesDTORead> readAll(Sort sort){
        return routesRepository.findAll(sort).stream().map(routesMapper::toDtoRead).toList();
    }

    @Override
    public Slice<RoutesDTORead> readAll(Pageable pageParam){
        return routesRepository.findAll(pageParam).map(routesMapper::toDtoRead);
    }

    @Override
    public Optional<RoutesDTORead> findById(Integer id){
        return routesRepository.findById(id).map(routesMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, RoutesDTOCreate routeDTOCreate){
        return routesRepository.findById(id).map(route -> {
            Object obj = routesMapper.updateRouteFromDtoCreate(routeDTOCreate, route);
            if (obj instanceof Routes)
                return routesMapper.toDtoRead(routesRepository.save((Routes) obj));
            else
                return obj;
        });
    }

    @Override
    public Boolean deleteById(Integer id){
        if (routesRepository.existsById(id)){
            routesRepository.deleteById(id);
            return true;
        } else
            return false;
    }
}
