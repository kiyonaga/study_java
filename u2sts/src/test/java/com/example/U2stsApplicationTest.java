package com.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = U2stsApplication.class)
//@WebAppConfiguration
public class U2stsApplicationTest
{
	private static final Logger logger = LoggerFactory.getLogger(U2stsApplicationTest.class);

	@Autowired
	private U2stsApplication main;

	@Test
	public void contextLoads()
	{
		logger.debug(main.mmm());
		Assert.assertEquals("", true, true);
	}

	@Test
	public void mmm()
	{
		logger.debug(main.mmm());
	}

}
