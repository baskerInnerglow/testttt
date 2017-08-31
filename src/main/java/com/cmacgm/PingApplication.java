package com.cmacgm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.cmacgm.repository.ApplicationRepository;
import com.cmacgm.repository.ApplicationUrlRepository;
import com.cmacgm.repository.ServerTypeRepository;
import com.cmacgm.repository.UserRepository;

@SpringBootApplication
public class PingApplication extends SpringBootServletInitializer  {

	@Autowired
    UserRepository userRepository;

	@Autowired
    ApplicationRepository applicationRepository;
	
	@Autowired
	ServerTypeRepository serverTypeRepository;
	
	@Autowired
	ApplicationUrlRepository applicationUrlRepository;



	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(PingApplication.class);
	    }

	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(PingApplication.class, args);
	    }

	


    /*@Override
    public void run(String... strings) throws Exception {

    
        
       
        
        ServerType serverTypeWeb=new ServerType("web", "web", new Date()); 
        ServerType serverTypeDb=new ServerType("db", "db", new Date()); 
        serverTypeRepository.save(serverTypeWeb);
        serverTypeRepository.save(serverTypeDb);

              Set applicationUrl = new HashSet<com.cmacgm.model.ApplicationUrl>(){{      
            add( new  com.cmacgm.model.ApplicationUrl(serverTypeWeb,"http://10.13.44.80:8080/JupiterLog/rest/log/pingUrl",
            		"10.13.44.80","0",true,"cira",new Date())); 
            add( new  com.cmacgm.model.ApplicationUrl(serverTypeDb,"http://10.13.44.80:8081/JupiterLog/rest/log/pingUrl",
            		"10.13.44.80","0",true,"emaildesk",new Date())); 
        }};
        applicationUrlRepository.save(applicationUrl);  
        com.cmacgm.model.Application applications= new  com.cmacgm.model.Application("Jupiter", "Jupiter",
        		new Date(),true,new Date(),3600000l,10000l,new Date(),applicationUrl);  
        
        applicationRepository.save(applications);
       // Users user = new Users("BASKER","SSC.BAMMU@CMA-CGM.COM",new Date(),applications);              
       // userRepository.save(user);
    }*/


}
