package com.service;

import org.springframework.stereotype.Service;

@Service
public interface ChiTietDonHangService {
	Double getTodayIncome();

	Double getTotalIncome();
}
