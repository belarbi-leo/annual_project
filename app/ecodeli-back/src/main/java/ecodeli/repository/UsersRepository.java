package ecodeli.repository;

import ecodeli.enumeratation.RoleEnum;
import ecodeli.modele.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("select user from Users user order by user.idUser")
    List<Users> findAllOrderById_user();

    //List<Users> findAllByRole(RoleEnum role);

    @Query("select user from Users user where user.role = ?1 order by user.idUser")
    List<Users> findAllByRoleOrderById_user(RoleEnum role);


    //Nouvelles méthodes avec JPARepository
    //List<Users> findAllByOrderByIdUserAsc(); pas besoin si on donne un objet sort en param

    List<Users> findAllByRole(RoleEnum role);

    List<Users> findAllByRole(RoleEnum role, Sort sort);

    Slice<Users> findAllByRole(RoleEnum role, Pageable pageable);

    Optional<Users> findByEmail(String email);

    //List<Users> findAllByRoleOrderByIdUserAsc(RoleEnum role);
}
