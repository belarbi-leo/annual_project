package ecodeli.modelMapper;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.modele.Ads;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T14:19:42+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class AdsMapperImpl implements AdsMapper {

    @Override
    public AdsDTORead toDtoRead(Ads ad) {
        if ( ad == null ) {
            return null;
        }

        AdsDTORead adsDTORead = new AdsDTORead();

        adsDTORead.setIdAd( ad.getIdAd() );
        adsDTORead.setUserCreator( ad.getUserCreator() );
        adsDTORead.setUserAccept( ad.getUserAccept() );
        adsDTORead.setSvc( ad.getSvc() );
        adsDTORead.setStatusAd( ad.getStatusAd() );
        adsDTORead.setDateCreationAd( ad.getDateCreationAd() );
        adsDTORead.setDateAcceptAd( ad.getDateAcceptAd() );
        adsDTORead.setDateStartAd( ad.getDateStartAd() );
        adsDTORead.setLocationStart( ad.getLocationStart() );
        adsDTORead.setSuiteStart( ad.getSuiteStart() );
        adsDTORead.setLocalityStart( ad.getLocalityStart() );
        adsDTORead.setStateStart( ad.getStateStart() );
        adsDTORead.setDateEndAd( ad.getDateEndAd() );
        adsDTORead.setLocationEnd( ad.getLocationEnd() );
        adsDTORead.setSuiteEnd( ad.getSuiteEnd() );
        adsDTORead.setLocalityEnd( ad.getLocalityEnd() );
        adsDTORead.setStateEnd( ad.getStateEnd() );
        adsDTORead.setDescriptionAd( ad.getDescriptionAd() );
        adsDTORead.setPriceAd( ad.getPriceAd() );
        adsDTORead.setPhotoAd( ad.getPhotoAd() );
        adsDTORead.setTitleAd( ad.getTitleAd() );

        return adsDTORead;
    }

    @Override
    public Ads fromDtoCreate(AdsDTOCreate adDtoCreate) {
        if ( adDtoCreate == null ) {
            return null;
        }

        Ads ads = new Ads();

        ads.setStatusAd( adDtoCreate.getStatusAd() );
        ads.setDateCreationAd( adDtoCreate.getDateCreationAd() );
        ads.setDateAcceptAd( adDtoCreate.getDateAcceptAd() );
        ads.setDateStartAd( adDtoCreate.getDateStartAd() );
        ads.setLocationStart( adDtoCreate.getLocationStart() );
        ads.setSuiteStart( adDtoCreate.getSuiteStart() );
        ads.setLocalityStart( adDtoCreate.getLocalityStart() );
        ads.setStateStart( adDtoCreate.getStateStart() );
        ads.setDateEndAd( adDtoCreate.getDateEndAd() );
        ads.setLocationEnd( adDtoCreate.getLocationEnd() );
        ads.setSuiteEnd( adDtoCreate.getSuiteEnd() );
        ads.setLocalityEnd( adDtoCreate.getLocalityEnd() );
        ads.setStateEnd( adDtoCreate.getStateEnd() );
        ads.setDescriptionAd( adDtoCreate.getDescriptionAd() );
        ads.setPriceAd( adDtoCreate.getPriceAd() );
        ads.setPhotoAd( adDtoCreate.getPhotoAd() );
        ads.setTitleAd( adDtoCreate.getTitleAd() );

        return ads;
    }

    @Override
    public Ads updateAdFromDtoCreate(AdsDTOCreate adDtoCreate, Ads ad) {
        if ( adDtoCreate == null ) {
            return ad;
        }

        if ( adDtoCreate.getStatusAd() != null ) {
            ad.setStatusAd( adDtoCreate.getStatusAd() );
        }
        if ( adDtoCreate.getDateCreationAd() != null ) {
            ad.setDateCreationAd( adDtoCreate.getDateCreationAd() );
        }
        if ( adDtoCreate.getDateAcceptAd() != null ) {
            ad.setDateAcceptAd( adDtoCreate.getDateAcceptAd() );
        }
        if ( adDtoCreate.getDateStartAd() != null ) {
            ad.setDateStartAd( adDtoCreate.getDateStartAd() );
        }
        if ( adDtoCreate.getLocationStart() != null ) {
            ad.setLocationStart( adDtoCreate.getLocationStart() );
        }
        if ( adDtoCreate.getSuiteStart() != null ) {
            ad.setSuiteStart( adDtoCreate.getSuiteStart() );
        }
        if ( adDtoCreate.getLocalityStart() != null ) {
            ad.setLocalityStart( adDtoCreate.getLocalityStart() );
        }
        if ( adDtoCreate.getStateStart() != null ) {
            ad.setStateStart( adDtoCreate.getStateStart() );
        }
        if ( adDtoCreate.getDateEndAd() != null ) {
            ad.setDateEndAd( adDtoCreate.getDateEndAd() );
        }
        if ( adDtoCreate.getLocationEnd() != null ) {
            ad.setLocationEnd( adDtoCreate.getLocationEnd() );
        }
        if ( adDtoCreate.getSuiteEnd() != null ) {
            ad.setSuiteEnd( adDtoCreate.getSuiteEnd() );
        }
        if ( adDtoCreate.getLocalityEnd() != null ) {
            ad.setLocalityEnd( adDtoCreate.getLocalityEnd() );
        }
        if ( adDtoCreate.getStateEnd() != null ) {
            ad.setStateEnd( adDtoCreate.getStateEnd() );
        }
        if ( adDtoCreate.getDescriptionAd() != null ) {
            ad.setDescriptionAd( adDtoCreate.getDescriptionAd() );
        }
        if ( adDtoCreate.getPriceAd() != null ) {
            ad.setPriceAd( adDtoCreate.getPriceAd() );
        }
        if ( adDtoCreate.getPhotoAd() != null ) {
            ad.setPhotoAd( adDtoCreate.getPhotoAd() );
        }
        if ( adDtoCreate.getTitleAd() != null ) {
            ad.setTitleAd( adDtoCreate.getTitleAd() );
        }

        return ad;
    }
}
