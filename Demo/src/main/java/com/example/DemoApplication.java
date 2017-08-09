package com.example;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@RestController
class BookingRestController {
	
	@RequestMapping("/bookings")
	Collection<Booking> booking(){
		return this.bookingRepository.findAll();
		
	}
	
	@Autowired BookingRepository bookingRepository;
}

@Component
class BookingCommandLineRunner implements CommandLineRunner{

	@Override
	public void run(String... arg0) throws Exception {
		
		for (Booking b : bookingRepository.findAll()) {
			System.out.println(b.toString());
		}
	}
	
	@Autowired BookingRepository bookingRepository;
	
}

interface BookingRepository extends JpaRepository<Booking, Long>{
	Collection<Booking> findByBookingName(String bookingName);
}

@Entity
class Booking {

	@Id @GeneratedValue
	private Long id;
	private String bookingName;

	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(String bookingName) {
		this.bookingName = bookingName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookingName() {
		return bookingName;
	}

	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}

	@Override
	public String toString() {
		return "Booking [bookingName=" + bookingName + "]";
	}

}
