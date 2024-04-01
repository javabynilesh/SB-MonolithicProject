package com.mono.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mono.exception.MsmeOrderNotFoundException;
import com.mono.payload.MsmeOrderDto;
import com.mono.service.IMsmeOrderService;

@RestController
@RequestMapping("/api/msme")
public class MsmeRestController {
	
	private Logger log = LoggerFactory.getLogger(MsmeRestController.class);
	
	@Autowired
	private IMsmeOrderService msmeOrderService;
	
	@PostMapping("/show")
	public ResponseEntity<String> show(){
		System.out.println("show method executed");
		return new ResponseEntity<String>("show method",HttpStatus.OK);
	}
	
	// save
	@PostMapping(value="/add",produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> addOrder(@RequestBody @Valid MsmeOrderDto msmeOrderDto) throws Exception{
		System.out.println("msmeOrderDto"+msmeOrderDto);
		ResponseEntity<String> response = null;
		Long msmeOrderId = msmeOrderService.addMsmeOrder(msmeOrderDto);
		if(msmeOrderId > 0) {
			response =  new ResponseEntity<String>("Order Created Successfully",HttpStatus.CREATED);
		}else{
			//response = new ResponseEntity<String>("Failed to create order",HttpStatus.INTERNAL_SERVER_ERROR);
			throw new Exception("Failed to create order");
		}
		return response;
	}
	
	// getAll -> return multiple object
	@GetMapping("/list")
	public ResponseEntity<List<MsmeOrderDto>> getAllOrders() {
		System.out.println("In getAllOrders()");
		log.debug("Fetching Msme Order list process started");
		List<MsmeOrderDto> allMsmeOrders = msmeOrderService.getAllMsmeOrders();
		log.debug("Fetching Msme Order list process completed");
		log.info("Msme Order list fetch success");
		return new ResponseEntity<List<MsmeOrderDto>>(allMsmeOrders,HttpStatus.OK);
	}
	
	// FetchOne -> getdata based on id return single object
	@GetMapping(value = "/edit/{id}")
	public ResponseEntity<?> getMsmeOrderById(@PathVariable("id") Long msmeOrderId) throws Exception{
		ResponseEntity<?> response = null;
		try {
			MsmeOrderDto msmeOrderDto = msmeOrderService.getMsmeOrderById(msmeOrderId);
			response = new ResponseEntity<MsmeOrderDto>(msmeOrderDto,HttpStatus.OK);
		} catch (MsmeOrderNotFoundException mnfe) {
			throw mnfe;
		}
		return response;
	}
	
	//update data based on id  
	@PutMapping(value="edit/{id}",produces="application/json",consumes="application/json")
	public ResponseEntity<String> updateMsmeOrder(@PathVariable("id") Long msmeOrderId,
												 @RequestBody @Valid MsmeOrderDto msmeOrderDto) throws Exception{
		log.debug("MsmeOrder updation process started");
		ResponseEntity<String> response = null;
		try {
			msmeOrderDto.setOrdId(msmeOrderId);
			msmeOrderService.updateMsmeOrder(msmeOrderDto);
			log.debug("Msme Order updation process completed");
			response = new ResponseEntity<String>("MsmeOrder"+msmeOrderDto.getOrdId()+" updated Successfully.",HttpStatus.OK);
		}catch (MsmeOrderNotFoundException mnfe) {
			throw mnfe;
		}
		return response;
	}
	
	
	//delete msme order by id
	@DeleteMapping(value="/delete/{id}",produces = "application/json",consumes = "application/json")
	public ResponseEntity<String> deleteMsmeOrder(@PathVariable("id") Long msmeOrderId) throws Exception{
		log.debug("MsmeOrder delete process started..");
		ResponseEntity<String> response = null;
		try {
			msmeOrderService.deleteMsmeOrderById(msmeOrderId);
			log.debug("MsmeOrder delete process completed..");
 			log.info("MsmeOrder deleted successfully..");
 			response = new ResponseEntity<String>("MsmeOrder"+ msmeOrderId +" deleted.",HttpStatus.OK);
		} catch (MsmeOrderNotFoundException mnfe) {
			throw mnfe;
		}
		
		return response;
	}
	
	
}
