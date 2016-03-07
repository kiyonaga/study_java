package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class U2stsApplication
{
	private static final Logger log = LoggerFactory.getLogger(U2stsApplication.class);

	@Autowired
	private HogeService svc;

	public static void main(String[] args)
	{
		// Webアプリの場合、try-with-resourcesだとコンテナが終了してしまう。
		//		try (ConfigurableApplicationContext ctx = SpringApplication.run(U2stsApplication.class, args))
		//		{
		//			U2stsApplication main = ctx.getBean(U2stsApplication.class);
		////			main.init();
		//		}

    SpringApplication app = new SpringApplication(U2stsApplication.class);
//    app.setBannerMode(Banner.Mode.OFF);

		ConfigurableApplicationContext ctx = app.run(args);
		U2stsApplication main = ctx.getBean(U2stsApplication.class);
		main.init();
	}

	public void init()
	{
		svc.init();
	}

	@RequestMapping("/")
	public String mmm()
	{
		svc.insertRandam();
		StringBuffer sb = new StringBuffer();
		svc.select().forEach(sb::append);
		log.info("Called mmm().");
		return sb.toString();
	}
}
