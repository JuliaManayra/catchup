package com.julia.api.catchup.service;

import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCrypt;

import org.springframework.stereotype.Service;

@Service
public class CriptografiaService {

	@Transactional
	public String encriptar(String chave) {

		return BCrypt.hashpw(chave, BCrypt.gensalt());
		
	}
	
	@Transactional
	public Boolean chaveIsValid(String chave, String hash){
		
		return 	BCrypt.checkpw(chave, hash);						
	 }
	
}
