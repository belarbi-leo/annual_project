package ecodeli.modelMapper;

import ecodeli.DTO.create.UsersDTOCreate;
import ecodeli.DTO.read.UsersDTORead;
import ecodeli.modele.Users;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class UsersMapperImpl implements UsersMapper {

    @Override
    public UsersDTORead toDtoRead(Users user) {
        if ( user == null ) {
            return null;
        }

        UsersDTORead usersDTORead = new UsersDTORead();

        usersDTORead.setAccountStatus( user.getAccountStatus() );
        usersDTORead.setBio( user.getBio() );
        usersDTORead.setCompanyName( user.getCompanyName() );
        usersDTORead.setCountry( user.getCountry() );
        usersDTORead.setDateAcceptCgu( user.getDateAcceptCgu() );
        usersDTORead.setDateAcceptCgv( user.getDateAcceptCgv() );
        usersDTORead.setDateRegistration( user.getDateRegistration() );
        usersDTORead.setDateStatus( user.getDateStatus() );
        usersDTORead.setEmail( user.getEmail() );
        usersDTORead.setFirstName( user.getFirstName() );
        usersDTORead.setIdUser( user.getIdUser() );
        usersDTORead.setLanguage( user.getLanguage() );
        usersDTORead.setLastName( user.getLastName() );
        usersDTORead.setLatitude( user.getLatitude() );
        usersDTORead.setLocality( user.getLocality() );
        usersDTORead.setLocation( user.getLocation() );
        usersDTORead.setLongitude( user.getLongitude() );
        usersDTORead.setPhoneNumber( user.getPhoneNumber() );
        usersDTORead.setPhotoUser( user.getPhotoUser() );
        usersDTORead.setPostalCode( user.getPostalCode() );
        usersDTORead.setRole( user.getRole() );
        usersDTORead.setSiret( user.getSiret() );
        usersDTORead.setState( user.getState() );
        usersDTORead.setSubscription( user.getSubscription() );
        usersDTORead.setSuite( user.getSuite() );

        return usersDTORead;
    }

    @Override
    public Users fromDtoCreate(UsersDTOCreate userDtoCreate) {
        if ( userDtoCreate == null ) {
            return null;
        }

        Users users = new Users();

        users.setAccountStatus( userDtoCreate.getAccountStatus() );
        users.setBio( userDtoCreate.getBio() );
        users.setCompanyName( userDtoCreate.getCompanyName() );
        users.setCountry( userDtoCreate.getCountry() );
        users.setDateAcceptCgu( userDtoCreate.getDateAcceptCgu() );
        users.setDateAcceptCgv( userDtoCreate.getDateAcceptCgv() );
        users.setDateRegistration( userDtoCreate.getDateRegistration() );
        users.setDateStatus( userDtoCreate.getDateStatus() );
        users.setEmail( userDtoCreate.getEmail() );
        users.setFirstName( userDtoCreate.getFirstName() );
        users.setLastName( userDtoCreate.getLastName() );
        users.setLatitude( userDtoCreate.getLatitude() );
        users.setLocality( userDtoCreate.getLocality() );
        users.setLocation( userDtoCreate.getLocation() );
        users.setLongitude( userDtoCreate.getLongitude() );
        users.setPassword( userDtoCreate.getPassword() );
        users.setPhoneNumber( userDtoCreate.getPhoneNumber() );
        users.setPhotoUser( userDtoCreate.getPhotoUser() );
        users.setPostalCode( userDtoCreate.getPostalCode() );
        users.setRole( userDtoCreate.getRole() );
        users.setSiret( userDtoCreate.getSiret() );
        users.setState( userDtoCreate.getState() );
        users.setSuite( userDtoCreate.getSuite() );

        return users;
    }

    @Override
    public Users updateUserFromDtoCreate(UsersDTOCreate userDtoCreate, Users user) {
        if ( userDtoCreate == null ) {
            return user;
        }

        if ( userDtoCreate.getAccountStatus() != null ) {
            user.setAccountStatus( userDtoCreate.getAccountStatus() );
        }
        if ( userDtoCreate.getBio() != null ) {
            user.setBio( userDtoCreate.getBio() );
        }
        if ( userDtoCreate.getCompanyName() != null ) {
            user.setCompanyName( userDtoCreate.getCompanyName() );
        }
        if ( userDtoCreate.getCountry() != null ) {
            user.setCountry( userDtoCreate.getCountry() );
        }
        if ( userDtoCreate.getDateAcceptCgu() != null ) {
            user.setDateAcceptCgu( userDtoCreate.getDateAcceptCgu() );
        }
        if ( userDtoCreate.getDateAcceptCgv() != null ) {
            user.setDateAcceptCgv( userDtoCreate.getDateAcceptCgv() );
        }
        if ( userDtoCreate.getDateRegistration() != null ) {
            user.setDateRegistration( userDtoCreate.getDateRegistration() );
        }
        if ( userDtoCreate.getDateStatus() != null ) {
            user.setDateStatus( userDtoCreate.getDateStatus() );
        }
        if ( userDtoCreate.getEmail() != null ) {
            user.setEmail( userDtoCreate.getEmail() );
        }
        if ( userDtoCreate.getFirstName() != null ) {
            user.setFirstName( userDtoCreate.getFirstName() );
        }
        if ( userDtoCreate.getLastName() != null ) {
            user.setLastName( userDtoCreate.getLastName() );
        }
        if ( userDtoCreate.getLatitude() != null ) {
            user.setLatitude( userDtoCreate.getLatitude() );
        }
        if ( userDtoCreate.getLocality() != null ) {
            user.setLocality( userDtoCreate.getLocality() );
        }
        if ( userDtoCreate.getLocation() != null ) {
            user.setLocation( userDtoCreate.getLocation() );
        }
        if ( userDtoCreate.getLongitude() != null ) {
            user.setLongitude( userDtoCreate.getLongitude() );
        }
        if ( userDtoCreate.getPassword() != null ) {
            user.setPassword( userDtoCreate.getPassword() );
        }
        if ( userDtoCreate.getPhoneNumber() != null ) {
            user.setPhoneNumber( userDtoCreate.getPhoneNumber() );
        }
        if ( userDtoCreate.getPhotoUser() != null ) {
            user.setPhotoUser( userDtoCreate.getPhotoUser() );
        }
        if ( userDtoCreate.getPostalCode() != null ) {
            user.setPostalCode( userDtoCreate.getPostalCode() );
        }
        if ( userDtoCreate.getRole() != null ) {
            user.setRole( userDtoCreate.getRole() );
        }
        if ( userDtoCreate.getSiret() != null ) {
            user.setSiret( userDtoCreate.getSiret() );
        }
        if ( userDtoCreate.getState() != null ) {
            user.setState( userDtoCreate.getState() );
        }
        if ( userDtoCreate.getSuite() != null ) {
            user.setSuite( userDtoCreate.getSuite() );
        }

        return user;
    }
}
