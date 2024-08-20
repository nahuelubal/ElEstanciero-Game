package ar.edu.utn.frc.tup.lciii.others;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class MappersConfigTest {

    private MappersConfig mappersConfig;

    @BeforeEach
    void setUp() {
        mappersConfig = new MappersConfig();
    }

    @Test
    void testModelMapperBean() {
        ModelMapper modelMapper = mappersConfig.modelMapper();
        assertNotNull(modelMapper, "ModelMapper bean should not be null");
    }

    @Test
    void testMergerMapperBean() {
        ModelMapper mergerMapper = mappersConfig.mergerMapper();
        assertNotNull(mergerMapper, "mergerMapper bean should not be null");
        assertTrue(mergerMapper.getConfiguration().getPropertyCondition().equals(Conditions.isNotNull()),
                "mergerMapper should be configured with Conditions.isNotNull()");
    }

}
