package ecodeli.modelMapper;

import ecodeli.DTO.create.UsersDTOCreate;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.modele.Users;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T13:42:24+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public UsersDTORead toDtoRead(Users user) {
        if ( user == null ) {
            return null;
        }

        UsersDTORead usersDTORead = new UsersDTORead();

        usersDTORead.setIdUser( user.getIdUser() );
        usersDTORead.setDateRegistration( user.getDateRegistration() );
        usersDTORead.setDateAcceptCgu( user.getDateAcceptCgu() );
        usersDTORead.setDateAcceptCgv( user.getDateAcceptCgv() );
        usersDTORead.setRole( user.getRole() );
        usersDTORead.setAccountStatus( user.getAccountStatus() );
        usersDTORead.setDateStatus( user.getDateStatus() );
        usersDTORead.setEmail( user.getEmail() );
        usersDTORead.setPhoneNumber( user.getPhoneNumber() );
        usersDTORead.setFirstName( user.getFirstName() );
        usersDTORead.setLastName( user.getLastName() );
        usersDTORead.setCompanyName( user.getCompanyName() );
        usersDTORead.setSiret( user.getSiret() );
        usersDTORead.setPhotoUser( user.getPhotoUser() );
        usersDTORead.setBio( user.getBio() );
        usersDTORead.setLocation( user.getLocation() );
        usersDTORead.setSuite( user.getSuite() );
        usersDTORead.setLocality( user.getLocality() );
        usersDTORead.setState( user.getState() );
        usersDTORead.setPostalCode( user.getPostalCode() );
        usersDTORead.setCountry( user.getCountry() );
        usersDTORead.setLatitude( user.getLatitude() );
        usersDTORead.setLongitude( user.getLongitude() );
        usersDTORead.setSubscription( user.getSubscription() );
        usersDTORead.setLanguage( user.getLanguage() );

        return usersDTORead;
    }

    @Override
    public Users fromDtoCreate(UsersDTOCreate userDtoCreate) {
        if ( userDtoCreate == null ) {
            return null;
        }

        Users users = new Users();

        users.setDateRegistration( userDtoCreate.getDateRegistration() );
        users.setDateAcceptCgu( userDtoCreate.getDateAcceptCgu() );
        users.setDateAcceptCgv( userDtoCreate.getDateAcceptCgv() );
        users.setRole( userDtoCreate.getRole() );
        users.setAccountStatus( userDtoCreate.getAccountStatus() );
        users.setDateStatus( userDtoCreate.getDateStatus() );
        users.setEmail( userDtoCreate.getEmail() );
        users.setPassword( userDtoCreate.getPassword() );
        users.setPhoneNumber( userDtoCreate.getPhoneNumber() );
        users.setFirstName( userDtoCreate.getFirstName() );
        users.setLastName( userDtoCreate.getLastName() );
        users.setCompanyName( userDtoCreate.getCompanyName() );
        users.setSiret( userDtoCreate.getSiret() );
        users.setPhotoUser( userDtoCreate.getPhotoUser() );
        users.setBio( userDtoCreate.getBio() );
        users.setLocation( userDtoCreate.getLocation() );
        users.setSuite( userDtoCreate.getSuite() );
        users.setLocality( userDtoCreate.getLocality() );
        users.setState( userDtoCreate.getState() );
        users.setPostalCode( userDtoCreate.getPostalCode() );
        users.setCountry( userDtoCreate.getCountry() );
        users.setLatitude( userDtoCreate.getLatitude() );
        users.setLongitude( userDtoCreate.getLongitude() );

        return users;
    }

    @Override
    public Users updateUserFromDtoCreate(UsersDTOCreate userDtoCreate, Users user) {
        if ( userDtoCreate == null ) {
            return user;
        }

        if ( userDtoCreate.getDateRegistration() != null ) {
            user.setDateRegistration( userDtoCreate.getDateRegistration() );
        }
        if ( userDtoCreate.getDateAcceptCgu() != null ) {
            user.setDateAcceptCgu( userDtoCreate.getDateAcceptCgu() );
        }
        if ( userDtoCreate.getDateAcceptCgv() != null ) {
            user.setDateAcceptCgv( userDtoCreate.getDateAcceptCgv() );
        }
        if ( userDtoCreate.getRole() != null ) {
            user.setRole( userDtoCreate.getRole() );
        }
        if ( userDtoCreate.getAccountStatus() != null ) {
            user.setAccountStatus( userDtoCreate.getAccountStatus() );
        }
        if ( userDtoCreate.getDateStatus() != null ) {
            user.setDateStatus( userDtoCreate.getDateStatus() );
        }
        if ( userDtoCreate.getEmail() != null ) {
            user.setEmail( userDtoCreate.getEmail() );
        }
        if ( userDtoCreate.getPassword() != null ) {
            user.setPassword( userDtoCreate.getPassword() );
        }
        if ( userDtoCreate.getPhoneNumber() != null ) {
            user.setPhoneNumber( userDtoCreate.getPhoneNumber() );
        }
        if ( userDtoCreate.getFirstName() != null ) {
            user.setFirstName( userDtoCreate.getFirstName() );
        }
        if ( userDtoCreate.getLastName() != null ) {
            user.setLastName( userDtoCreate.getLastName() );
        }
        if ( userDtoCreate.getCompanyName() != null ) {
            user.setCompanyName( userDtoCreate.getCompanyName() );
        }
        if ( userDtoCreate.getSiret() != null ) {
            user.setSiret( userDtoCreate.getSiret() );
        }
        if ( userDtoCreate.getPhotoUser() != null ) {
            user.setPhotoUser( userDtoCreate.getPhotoUser() );
        }
        if ( userDtoCreate.getBio() != null ) {
            user.setBio( userDtoCreate.getBio() );
        }
        if ( userDtoCreate.getLocation() != null ) {
            user.setLocation( userDtoCreate.getLocation() );
        }
        if ( userDtoCreate.getSuite() != null ) {
            user.setSuite( userDtoCreate.getSuite() );
        }
        if ( userDtoCreate.getLocality() != null ) {
            user.setLocality( userDtoCreate.getLocality() );
        }
        if ( userDtoCreate.getState() != null ) {
            user.setState( userDtoCreate.getState() );
        }
        if ( userDtoCreate.getPostalCode() != null ) {
            user.setPostalCode( userDtoCreate.getPostalCode() );
        }
        if ( userDtoCreate.getCountry() != null ) {
            user.setCountry( userDtoCreate.getCountry() );
        }
        if ( userDtoCreate.getLatitude() != null ) {
            user.setLatitude( userDtoCreate.getLatitude() );
        }
        if ( userDtoCreate.getLongitude() != null ) {
            user.setLongitude( userDtoCreate.getLongitude() );
        }

        return user;
    }
}
