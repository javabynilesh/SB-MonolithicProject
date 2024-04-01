package com.mono.service;

import java.util.List;

import com.mono.payload.MsmeOrderDto;

public interface IMsmeOrderService {
	public Long addMsmeOrder(MsmeOrderDto msmeOrderDto);
	public List<MsmeOrderDto> getAllMsmeOrders();
	public MsmeOrderDto getMsmeOrderById(Long msmeOrderId);
	public void updateMsmeOrder(MsmeOrderDto msmeOrderDto);
	public void deleteMsmeOrderById(Long msmeOrderId);
}
