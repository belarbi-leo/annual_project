package com.pa2aresgi.pa2a.modelMapper;

import com.pa2aresgi.pa2a.DTO.create.MaterielsDTOCreate;
import com.pa2aresgi.pa2a.DTO.read.MaterielsDTORead;
import com.pa2aresgi.pa2a.modele.Materiels;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-02T20:10:08+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.2 (Homebrew)"
)
@Component
public class MaterielsMapperImpl implements MaterielsMapper {

    @Override
    public MaterielsDTORead toDtoRead(Materiels materiel) {
        if ( materiel == null ) {
            return null;
        }

        MaterielsDTORead materielsDTORead = new MaterielsDTORead();

        materielsDTORead.setIdMateriel( materiel.getIdMateriel() );
        materielsDTORead.setSvc( materiel.getSvc() );
        materielsDTORead.setNameMateriel( materiel.getNameMateriel() );
        materielsDTORead.setDescriptionMateriel( materiel.getDescriptionMateriel() );

        return materielsDTORead;
    }

    @Override
    public Materiels fromDtoCreate(MaterielsDTOCreate materielDtoCreate) {
        if ( materielDtoCreate == null ) {
            return null;
        }

        Materiels materiels = new Materiels();

        materiels.setSvc( materielDtoCreate.getSvc() );
        materiels.setNameMateriel( materielDtoCreate.getNameMateriel() );
        materiels.setDescriptionMateriel( materielDtoCreate.getDescriptionMateriel() );

        return materiels;
    }
}
