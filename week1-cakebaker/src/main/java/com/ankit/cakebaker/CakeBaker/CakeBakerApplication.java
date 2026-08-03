package com.ankit.cakebaker.CakeBaker;

import com.ankit.cakebaker.CakeBaker.service.CakeBaker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CakeBakerApplication implements CommandLineRunner {

    private final CakeBaker cakeBaker;

    public CakeBakerApplication(CakeBaker cakeBaker){
        this.cakeBaker = cakeBaker;
    }

	public static void main(String[] args) {
		SpringApplication.run(CakeBakerApplication.class, args);
	}


    @Override
    public void run(String... args) throws Exception {
        cakeBaker.bakeCake();
    }
}
