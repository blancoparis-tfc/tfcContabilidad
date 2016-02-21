package org.dbp.service.impl;

import org.dbp.service.VersionService;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl implements VersionService{
	
	@Override
	public String getVersion(){
		return "1.0.0";
	}

}
