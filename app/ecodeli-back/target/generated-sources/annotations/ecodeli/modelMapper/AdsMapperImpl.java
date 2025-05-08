package ecodeli.modelMapper;

import ecodeli.DTO.create.AdsDTOCreate;
import ecodeli.DTO.read.AdsDTORead;
import ecodeli.modele.Ads;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-08T15:25:52+0200",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.42.0.z20250331-1358, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class AdsMapperImpl implements AdsMapper {

    @Override
    public AdsDTORead toDtoRead(Ads ad) {
        if ( ad == null ) {
            return null;
        }

        AdsDTORead adsDTORead = new AdsDTORead();

        adsDTORead.setDateAcceptAd( ad.getDateAcceptAd() );
        adsDTORead.setDateCreationAd( ad.getDateCreationAd() );
        adsDTORead.setDateEndAd( ad.getDateEndAd() );
        adsDTORead.setDateStartAd( ad.getDateStartAd() );
        adsDTORead.setDescriptionAd( ad.getDescriptionAd() );
        adsDTORead.setIdAd( ad.getIdAd() );
        adsDTORead.setLocalityEnd( ad.getLocalityEnd() );
        adsDTORead.setLocalityStart( ad.getLocalityStart() );
        adsDTORead.setLocationEnd( ad.getLocationEnd() );
        adsDTORead.setLocationStart( ad.getLocationStart() );
        adsDTORead.setPhotoAd( ad.getPhotoAd() );
        adsDTORead.setPriceAd( ad.getPriceAd() );
        adsDTORead.setStateEnd( ad.getStateEnd() );
        adsDTORead.setStateStart( ad.getStateStart() );
        adsDTORead.setStatusAd( ad.getStatusAd() );
        adsDTORead.setSuiteEnd( ad.getSuiteEnd() );
        adsDTORead.setSuiteStart( ad.getSuiteStart() );
        adsDTORead.setSvc( ad.getSvc() );
        adsDTORead.setTitleAd( ad.getTitleAd() );
        adsDTORead.setUserAccept( ad.getUserAccept() );
        adsDTORead.setUserCreator( ad.getUserCreator() );

        return adsDTORead;
    }

    @Override
    public Ads fromDtoCreate(AdsDTOCreate adDtoCreate) {
        if ( adDtoCreate == null ) {
            return null;
        }

        Ads ads = new Ads();

        ads.setDateAcceptAd( adDtoCreate.getDateAcceptAd() );
        ads.setDateCreationAd( adDtoCreate.getDateCreationAd() );
        ads.setDateEndAd( adDtoCreate.getDateEndAd() );
        ads.setDateStartAd( adDtoCreate.getDateStartAd() );
        ads.setDescriptionAd( adDtoCreate.getDescriptionAd() );
        ads.setLocalityEnd( adDtoCreate.getLocalityEnd() );
        ads.setLocalityStart( adDtoCreate.getLocalityStart() );
        ads.setLocationEnd( adDtoCreate.getLocationEnd() );
        ads.setLocationStart( adDtoCreate.getLocationStart() );
        ads.setPhotoAd( adDtoCreate.getPhotoAd() );
        ads.setPriceAd( adDtoCreate.getPriceAd() );
        ads.setStateEnd( adDtoCreate.getStateEnd() );
        ads.setStateStart( adDtoCreate.getStateStart() );
        ads.setStatusAd( adDtoCreate.getStatusAd() );
        ads.setSuiteEnd( adDtoCreate.getSuiteEnd() );
        ads.setSuiteStart( adDtoCreate.getSuiteStart() );
        ads.setTitleAd( adDtoCreate.getTitleAd() );

        return ads;
    }

    @Override
    public Ads updateAdFromDtoCreate(AdsDTOCreate adDtoCreate, Ads ad) {
        if ( adDtoCreate == null ) {
            return ad;
        }

        if ( adDtoCreate.getDateAcceptAd() != null ) {
            ad.setDateAcceptAd( adDtoCreate.getDateAcceptAd() );
        }
        if ( adDtoCreate.getDateCreationAd() != null ) {
            ad.setDateCreationAd( adDtoCreate.getDateCreationAd() );
        }
        if ( adDtoCreate.getDateEndAd() != null ) {
            ad.setDateEndAd( adDtoCreate.getDateEndAd() );
        }
        if ( adDtoCreate.getDateStartAd() != null ) {
            ad.setDateStartAd( adDtoCreate.getDateStartAd() );
        }
        if ( adDtoCreate.getDescriptionAd() != null ) {
            ad.setDescriptionAd( adDtoCreate.getDescriptionAd() );
        }
        if ( adDtoCreate.getLocalityEnd() != null ) {
            ad.setLocalityEnd( adDtoCreate.getLocalityEnd() );
        }
        if ( adDtoCreate.getLocalityStart() != null ) {
            ad.setLocalityStart( adDtoCreate.getLocalityStart() );
        }
        if ( adDtoCreate.getLocationEnd() != null ) {
            ad.setLocationEnd( adDtoCreate.getLocationEnd() );
        }
        if ( adDtoCreate.getLocationStart() != null ) {
            ad.setLocationStart( adDtoCreate.getLocationStart() );
        }
        if ( adDtoCreate.getPhotoAd() != null ) {
            ad.setPhotoAd( adDtoCreate.getPhotoAd() );
        }
        if ( adDtoCreate.getPriceAd() != null ) {
            ad.setPriceAd( adDtoCreate.getPriceAd() );
        }
        if ( adDtoCreate.getStateEnd() != null ) {
            ad.setStateEnd( adDtoCreate.getStateEnd() );
        }
        if ( adDtoCreate.getStateStart() != null ) {
            ad.setStateStart( adDtoCreate.getStateStart() );
        }
        if ( adDtoCreate.getStatusAd() != null ) {
            ad.setStatusAd( adDtoCreate.getStatusAd() );
        }
        if ( adDtoCreate.getSuiteEnd() != null ) {
            ad.setSuiteEnd( adDtoCreate.getSuiteEnd() );
        }
        if ( adDtoCreate.getSuiteStart() != null ) {
            ad.setSuiteStart( adDtoCreate.getSuiteStart() );
        }
        if ( adDtoCreate.getTitleAd() != null ) {
            ad.setTitleAd( adDtoCreate.getTitleAd() );
        }

        return ad;
    }
}
