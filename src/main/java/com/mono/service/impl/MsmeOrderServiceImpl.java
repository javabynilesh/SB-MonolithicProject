package com.mono.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mono.entity.Address;
import com.mono.entity.MsmeOrder;
import com.mono.exception.MsmeOrderNotFoundException;
import com.mono.payload.AddressDto;
import com.mono.payload.MsmeOrderDto;
import com.mono.repo.MsmeOrderRepository;
import com.mono.service.IMsmeOrderService;

@Service
public class MsmeOrderServiceImpl implements IMsmeOrderService{
	
	@Autowired
	private MsmeOrderRepository repo;
	
	@Override
	public Long addMsmeOrder(MsmeOrderDto msmeOrderDto) {
		MsmeOrder msmeOrderEntity = new MsmeOrder();
		BeanUtils.copyProperties(msmeOrderDto, msmeOrderEntity);
		
		//get child
		List<AddressDto> addrListFromMsmeOrderDto = msmeOrderDto.getAddress();
		
		//make arraylist for Address Entity
		List<Address> addressList = new ArrayList<>();
		
		//approach-1
		for(AddressDto addr : addrListFromMsmeOrderDto) {
			//create object for Address Entity
			Address addressEntity = new Address();
			BeanUtils.copyProperties(addr, addressEntity);
			addressList.add(addressEntity);
		}
		
		//approach-2
		/*addrListFromMsmeOrderDto.forEach((addr)->{
			//create object for Address Entity
			Address addressEntity = new Address();
			BeanUtils.copyProperties(addr, addressEntity);
			addressList.add(addressEntity);
		});*/
		
		msmeOrderEntity.setAddress(addressList);
		
		System.out.println("msmeOrderEntity:"+msmeOrderEntity);
		return repo.save(msmeOrderEntity).getOrdId();
	}

	@Override
	public List<MsmeOrderDto> getAllMsmeOrders() {
		List<MsmeOrder> msmeOrdersList = repo.findAll();
		
		List<MsmeOrderDto> entitiesDtoList = new ArrayList<>();
		
		for(MsmeOrder orderEntity : msmeOrdersList) {
			MsmeOrderDto msmeOrderDto = new MsmeOrderDto();
			BeanUtils.copyProperties(orderEntity, msmeOrderDto);
			entitiesDtoList.add(msmeOrderDto);
		}
		
		return entitiesDtoList;
	}

	@Override
	public MsmeOrderDto getMsmeOrderById(Long msmeOrderId) {
		Optional<MsmeOrder> getMsmeOrderEntity = repo.findById(msmeOrderId);
		
		//MsmeOrderDto msmeOrderDto = new MsmeOrderDto();
		if(getMsmeOrderEntity.isPresent()) {
			MsmeOrder msmeOrderEntity = getMsmeOrderEntity.get();
			MsmeOrderDto msmeOrderDto = new MsmeOrderDto();
			BeanUtils.copyProperties(msmeOrderEntity, msmeOrderDto);
			List<Address> addrList = msmeOrderEntity.getAddress();
			List<AddressDto> addressDtoList = new ArrayList<>();
			
			//approach-1
			for(Address address :addrList) {
				AddressDto addressDto = new AddressDto();
				BeanUtils.copyProperties(address, addressDto);
				
				addressDtoList.add(addressDto);
			}
			
			//approach-2 
			/*addrList.forEach(address ->{
				AddressDto addressDto = new AddressDto();
				BeanUtils.copyProperties(address, addressDto);
				addressDto.setOrderId(address.getMsmeOrder().getOrdId());
				addressDtoList.add(addressDto);
			});*/
			
			msmeOrderDto.setAddress(addressDtoList);
			return msmeOrderDto;
		}else {
			throw new MsmeOrderNotFoundException("MsmeOrder "+msmeOrderId+" not exist."); 
		}
	}

	@Override
	public void updateMsmeOrder(MsmeOrderDto msmeOrderDto) {
		MsmeOrder msmeOrderEntity = new MsmeOrder();
		if(msmeOrderDto.getOrdId() == null || !repo.existsById(msmeOrderDto.getOrdId())) {
			throw new MsmeOrderNotFoundException("MsmeOrder "+msmeOrderDto.getOrdId()+" not exist.");
		} else {
			//BeanUtils.copyProperties(msmeOrderDto, msmeOrderEntity);
			msmeOrderEntity = convertMsmeDtoToMsme(msmeOrderDto);
			
			//get child
			List<AddressDto> addrListFromMsmeOrderDto = msmeOrderDto.getAddress();
			
			//make arraylist for Address Entity
			List<Address> addressList = new ArrayList<>();
			
			//approach-1
			for(AddressDto addr : addrListFromMsmeOrderDto) {
				//create object for Address Entity
				Address addressEntity = new Address();
				BeanUtils.copyProperties(addr, addressEntity);
				addressEntity.setMsmeOrder(msmeOrderEntity);
				addressList.add(addressEntity);
			}
			msmeOrderEntity.setAddress(addressList);
			repo.save(msmeOrderEntity);
		}
	}
	
	private MsmeOrder convertMsmeDtoToMsme(MsmeOrderDto msmeOrderDto) {
		MsmeOrder msmeOrder = new MsmeOrder();
		msmeOrder.setOrdId(msmeOrderDto.getOrdId());
		msmeOrder.setCustomer(msmeOrderDto.getCustomer());
		msmeOrder.setAge(msmeOrderDto.getAge());
		msmeOrder.setMobileNo(msmeOrderDto.getMobileNo());
		msmeOrder.setEmail(msmeOrderDto.getEmail());
		msmeOrder.setIfscCode(msmeOrderDto.getIfscCode());
		msmeOrder.setSelCategory(msmeOrderDto.getSelCategory());
		msmeOrder.setDob(msmeOrderDto.getDob());
		return msmeOrder;
	}
	
	@Override
	public void deleteMsmeOrderById(Long msmeOrderId) {
		repo.deleteById(msmeOrderId);
	}

}
