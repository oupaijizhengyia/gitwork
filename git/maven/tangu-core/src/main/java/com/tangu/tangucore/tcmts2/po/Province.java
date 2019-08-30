package com.tangu.tangucore.tcmts2.po;

import lombok.Data;

import java.util.List;

/**
 * 省
 * @author Administrator
 *
 */
@Data
public class Province {
	private String citycode;
	private String adcode;
	private String name;
	private String center;
	private String level;
	List<City> districts;
}
