package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.AdsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.AdsDTORead;
import com.pa2aresgi.pa2a.modele.Ads;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T10:08:36+0200",
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
        adsDTORead.setDateEndAd( ad.getDateEndAd() );
        adsDTORead.setDescriptionAd( ad.getDescriptionAd() );
        adsDTORead.setPriceAd( ad.getPriceAd() );
        adsDTORead.setPhotoAd( ad.getPhotoAd() );

        return adsDTORead;
    }

    @Override
    public Ads fromDtoCreate(AdsDTOCreate adDtoCreate) {
        if ( adDtoCreate == null ) {
            return null;
        }

        Ads ads = new Ads();

        ads.setUserCreator( adDtoCreate.getUserCreator() );
        ads.setUserAccept( adDtoCreate.getUserAccept() );
        ads.setSvc( adDtoCreate.getSvc() );
        ads.setStatusAd( adDtoCreate.getStatusAd() );
        ads.setDateCreationAd( adDtoCreate.getDateCreationAd() );
        ads.setDateAcceptAd( adDtoCreate.getDateAcceptAd() );
        ads.setDateStartAd( adDtoCreate.getDateStartAd() );
        ads.setDateEndAd( adDtoCreate.getDateEndAd() );
        ads.setDescriptionAd( adDtoCreate.getDescriptionAd() );
        ads.setPriceAd( adDtoCreate.getPriceAd() );
        ads.setPhotoAd( adDtoCreate.getPhotoAd() );

        return ads;
    }
}
