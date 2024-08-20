package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.IBoxRepository;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.box.TaxBox;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import ar.edu.utn.frc.tup.lciii.services.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoxServiceImplTest {

    @Mock
    private IBoxRepository boxRepository;

    @Mock
    private TaxService taxService;

    @Mock
    private DeedService deedService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BoxServiceImpl boxService;

    @BeforeEach
    void setUp() {
        boxService = new BoxServiceImpl(boxRepository, taxService, deedService, modelMapper);
    }

    @Test
    void testGetBoxes() {
        BoxTypeEntity boxTypeEntity = new BoxTypeEntity();
        boxTypeEntity.setBoxTypeName(BoxType.TAX_BOX);

        BoxEntity boxEntity = new BoxEntity();
        boxEntity.setIdBox(1);
        boxEntity.setBoxType(boxTypeEntity);

        List<BoxEntity> boxEntities = Collections.singletonList(boxEntity);
        when(boxRepository.getAllBoxes()).thenReturn(boxEntities);

        TaxBox taxBox = new TaxBox();
        taxBox.setIdBox(1);
        taxBox.setTaxAmount(100);
        when(modelMapper.map(boxEntity, TaxBox.class)).thenReturn(taxBox);
        when(taxService.getTaxByBoxId(1)).thenReturn(taxBox);

        List<AbstractBox> boxes = boxService.getBoxes();

        assertEquals(1, boxes.size());
        assertEquals(TaxBox.class, boxes.get(0).getClass());
        assertEquals(100, ((TaxBox) boxes.get(0)).getTaxAmount());
    }

    @Test
    void testGetPlayersByBox() {
        GameStyleEntity gameStyleEntity = new GameStyleEntity();
        gameStyleEntity.setGameStyle(BotPlayerTypes.CONSERVATIVE);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setGameStyle(gameStyleEntity);
        playerEntity.setIdPlayer(1);

        List<PlayerEntity> playerEntities = Collections.singletonList(playerEntity);
        when(boxRepository.getAllPlayersByBoxId(1)).thenReturn(playerEntities);

        ConservativeBotPlayer conservativeBotPlayer = new ConservativeBotPlayer();
        conservativeBotPlayer.setIdPlayer(1);
        when(modelMapper.map(playerEntity, ConservativeBotPlayer.class)).thenReturn(conservativeBotPlayer);

        List<AbstractPlayer> players = boxService.getPlayersByBox(1);

        assertEquals(1, players.size());
        assertEquals(ConservativeBotPlayer.class, players.get(0).getClass());
    }

    @Test
    void testSaveOrUpdateBox() {
        AbstractBox abstractBox = new TaxBox();
        abstractBox.setIdBox(1);
        List<AbstractPlayer> players = new ArrayList<>();
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.setIdPlayer(1);
        players.add(humanPlayer);
        abstractBox.setPlayers(players);

        BoxEntity boxEntity = new BoxEntity();
        when(modelMapper.map(abstractBox, BoxEntity.class)).thenReturn(boxEntity);

        PlayerEntity playerEntity = new PlayerEntity();
        when(modelMapper.map(humanPlayer, PlayerEntity.class)).thenReturn(playerEntity);

        boxService.saveOrUpdateBox(abstractBox);

        verify(boxRepository, times(1)).saveOrUpdateBox(boxEntity, Collections.singletonList(playerEntity));
    }

    @Test
    void testFindBoxById() {
        BoxEntity boxEntity = new BoxEntity();
        boxEntity.setIdBox(1);
        BoxTypeEntity boxTypeEntity = new BoxTypeEntity();
        boxTypeEntity.setBoxTypeName(BoxType.TAX_BOX);
        boxEntity.setBoxType(boxTypeEntity);

        when(boxRepository.findBoxById(1)).thenReturn(boxEntity);

        TaxBox taxBox = new TaxBox();
        taxBox.setIdBox(1);
        taxBox.setBoxType(BoxType.TAX_BOX);
        when(modelMapper.map(boxEntity, AbstractBox.class)).thenReturn(taxBox);

        AbstractBox result = boxService.findBoxById(1);

        assertEquals(taxBox, result);
        assertEquals(BoxType.TAX_BOX, result.getBoxType());
    }

    @Test
    void testFindBoxByIdNotFound() {
        when(boxRepository.findBoxById(1)).thenReturn(null);

        AbstractBox result = boxService.findBoxById(1);

        assertNull(result);
    }

    @Test
    void testGetBoxTypeByName() {
        BoxType boxType = BoxType.TAX_BOX;
        BoxTypeEntity boxTypeEntity = new BoxTypeEntity();

        when(boxRepository.getBoxTypeByName(boxType)).thenReturn(boxTypeEntity);

        BoxTypeEntity result = boxService.getBoxTypeByName(boxType);

        assertEquals(boxTypeEntity, result);
    }
}
