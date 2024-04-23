package com.virtusa.eg.sequencegenerator;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;





public class StringPrefixedSequenceIdGenerator implements IdentifierGenerator{
	Logger log = Logger.getLogger(StringPrefixedSequenceIdGenerator.class);
	@Override
	public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
		
		try {
			Random random = SecureRandom.getInstanceStrong();
			return "CUST_"+random.nextInt(97);
		} catch (NoSuchAlgorithmException e) {
			
			log.info(e);
		}
		
		return null;
	}
	 
	


}
