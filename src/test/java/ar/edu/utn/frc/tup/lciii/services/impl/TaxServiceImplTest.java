package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.TaxEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.ITaxRepository;
import ar.edu.utn.frc.tup.lciii.model.box.TaxBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaxServiceImplTest {

    @Mock
    private ITaxRepository taxRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TaxServiceImpl taxService;

    @BeforeEach
    void setUp() {
        taxService = new TaxServiceImpl(taxRepository, modelMapper);
    }

    @Test
    void testGetTaxByBoxId() {
        int boxId = 1;
        TaxEntity taxEntity = new TaxEntity();
        TaxBox taxBox = new TaxBox();

        when(taxRepository.getTaxEntityByBoxId(boxId)).thenReturn(taxEntity);
        when(modelMapper.map(taxEntity, TaxBox.class)).thenReturn(taxBox);

        TaxBox result = taxService.getTaxByBoxId(boxId);

        assertEquals(taxBox, result);
    }
}
