package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.AdsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.AdsDTORead;
import com.pa2aresgi.pa2a.modele.Ads;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-25T10:18:00+0200",
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
        adsDTORead.setPhotoAd( ad.getPhotoAd() );
        adsDTORead.setPriceAd( ad.getPriceAd() );
        adsDTORead.setStatusAd( ad.getStatusAd() );
        adsDTORead.setSvc( ad.getSvc() );
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
        ads.setPhotoAd( adDtoCreate.getPhotoAd() );
        ads.setPriceAd( adDtoCreate.getPriceAd() );
        ads.setStatusAd( adDtoCreate.getStatusAd() );
        ads.setSvc( adDtoCreate.getSvc() );
        ads.setUserAccept( adDtoCreate.getUserAccept() );
        ads.setUserCreator( adDtoCreate.getUserCreator() );

        return ads;
    }
}
