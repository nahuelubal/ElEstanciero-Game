package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.TaxEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.ITaxRepository;
import ar.edu.utn.frc.tup.lciii.model.box.TaxBox;
import ar.edu.utn.frc.tup.lciii.services.TaxService;
import org.modelmapper.ModelMapper;

public class TaxServiceImpl implements TaxService {

	private final ITaxRepository taxRepository;
	private final ModelMapper modelMapper;
	public TaxServiceImpl(ITaxRepository taxRepository, ModelMapper modelMapper) {
		this.taxRepository = taxRepository;
		this.modelMapper = modelMapper;
	}

	private static class Holder {
		private static TaxServiceImpl INSTANCE;
	}

	public static TaxServiceImpl getInstance(ITaxRepository taxRepository, ModelMapper modelMapper) {
		if (Holder.INSTANCE == null) {
			Holder.INSTANCE = new TaxServiceImpl(taxRepository, modelMapper);
		}
		return Holder.INSTANCE;
	}

	@Override
	public TaxBox getTaxByBoxId(int boxId) {
		TaxEntity taxEntity = taxRepository.getTaxEntityByBoxId(boxId);
		return modelMapper.map(taxEntity, TaxBox.class);
	}
}
