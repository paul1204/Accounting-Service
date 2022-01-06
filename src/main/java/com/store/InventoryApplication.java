package com.store;

import com.store.inventory.Inventory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		//Inventory i = new Inventory();

		SpringApplication.run(InventoryApplication.class, args);

	}

}
