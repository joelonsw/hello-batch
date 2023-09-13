package com.example.hellobatch.job.filereadwrite;

import com.example.hellobatch.job.filereadwrite.dto.Player;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PlayerFieldSetMapper implements FieldSetMapper<Player> {
    @Override
    public Player mapFieldSet(FieldSet fieldSet) throws BindException {
        return new Player(
            fieldSet.readString(0),
            fieldSet.readString(1),
            fieldSet.readString(2),
            fieldSet.readString(3),
            fieldSet.readString(4),
            fieldSet.readString(5)
        );
    }
}
