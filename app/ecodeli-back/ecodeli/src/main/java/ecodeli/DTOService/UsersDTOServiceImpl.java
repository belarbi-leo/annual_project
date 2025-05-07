package ecodeli.DTOService;


import ecodeli.DTO.create.UsersDTOCreate;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.enumeratation.RoleEnum;
import ecodeli.modelMapper.subClassImpl.UsersMapperSubClassImpl;
import ecodeli.modele.Users;
import ecodeli.repository.UsersRepository;
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
public class UsersDTOServiceImpl implements UsersDTOService {
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapperSubClassImpl usersMapper;

    @Override
    public Object create(UsersDTOCreate userDtoCreate) {
        if (userDtoCreate.getDateRegistration() == null) userDtoCreate.setDateRegistration(Timestamp.valueOf(LocalDateTime.now()));
        if (userDtoCreate.getRole() == null) userDtoCreate.setRole(RoleEnum.part);
        if (userDtoCreate.getDateStatus() == null) userDtoCreate.setDateStatus(Timestamp.valueOf(LocalDateTime.now()));

        Object obj = usersMapper.fromDtoCreate(userDtoCreate);
        if (obj instanceof Users){
            return usersMapper.toDtoRead(usersRepository.save((Users)obj));
        } else {
            return obj;
        }
        /*Users user = usersMapper.fromDtoCreate(userDtoCreate);
        return usersMapper.toDtoRead(usersRepository.save(user));*/
    }

    @Override
    public List<UsersDTORead> readAll() {
        return usersRepository.findAll().stream().map(usersMapper::toDtoRead).toList()/*collect(Collectors.toList())*/;
    }

    @Override
    public List<UsersDTORead> readAll(Sort sort) {
        return usersRepository.findAll(sort).stream().map(usersMapper::toDtoRead).toList()/*collect(Collectors.toList())*/;
    }

    @Override
    public Slice<UsersDTORead> readAll(Pageable pageParam) {
        return usersRepository.findAll(pageParam).map(usersMapper::toDtoRead);
    }

    @Override
    public List<UsersDTORead> readAllByRole(RoleEnum role){
        return usersRepository.findAllByRole(role).stream().map(usersMapper::toDtoRead).toList()/*collect(Collectors.toList())*/;
    }

    @Override
    public List<UsersDTORead> readAllByRole(RoleEnum role, Sort sort){
        return usersRepository.findAllByRole(role,sort).stream().map(usersMapper::toDtoRead).toList()/*collect(Collectors.toList())*/;
    }

    @Override
    public Slice<UsersDTORead> readAllByRole(RoleEnum role, Pageable pageParam) {
        return usersRepository.findAllByRole(role, pageParam).map(usersMapper::toDtoRead);
    }

    @Override
    public Optional<UsersDTORead> findById(Integer id){
        return usersRepository.findById(id).map(usersMapper::toDtoRead);
        /*Users user = usersRepository.findById(id).orElse(null);
        if (user != null) {
            return Optional.of(usersMapper.toDtoRead(user));
        }
        return Optional.empty();*/
    }

    public Optional<UsersDTORead> findByEmail(String email){
        return usersRepository.findByEmail(email).map(usersMapper::toDtoRead);
    }

    @Override
    public Optional<Object> update(Integer id, UsersDTOCreate userDtoCreate) {
        return usersRepository.findById(id).map(usr -> {
            Object obj = usersMapper.updateUserFromDtoCreate(userDtoCreate, usr);
            if (obj instanceof Users){
                return usersMapper.toDtoRead(usersRepository.save((Users)obj));
            } else {
                return obj;
            }
        });
    }
    /*public Optional<UsersDTORead> update(Integer id, UsersDTOCreate userDtoCreate) {
        return usersRepository.findById(id).map(usr -> {
           usersMapper.updateUserFromDtoCreate(userDtoCreate, usr);
           return usersMapper.toDtoRead(usersRepository.save(usr));
        });
    }*/

    @Override
    public Boolean deleteById(Integer id) {
        if (usersRepository.existsById(id)) {
            usersRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}