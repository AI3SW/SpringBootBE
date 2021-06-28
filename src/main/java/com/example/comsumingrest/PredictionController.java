package com.example.comsumingrest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PredictionController {
	private static final String template = "Hello, %s";
	private final AtomicLong counter = new AtomicLong();
	//private static final String predictionURL = "http://10.2.117.32:5000";
	private static final String predictionURL = "https://7cc1981e-f1fd-4e64-9836-03183317add0.mock.pstmn.io";
	
	@Bean
	@RequestMapping("/Quote")
	String getQuote() {
		RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject(
				"https://quoters.apps.pcfone.io/api/random",
				Quote.class);
		//return quote.toString() + counter.incrementAndGet();
		return quote.toString();
	}
	
	@RequestMapping("/predict")
	String getPrediction() {
		
		BufferedImage src_img = null;
		BufferedImage ref_img = null;
		String src = null;
		String ref = null;
		
		try {
		    src_img = ImageIO.read(getClass().getResource("src.jpg"));
		    ref_img = ImageIO.read(getClass().getResourceAsStream("ref.jpg"));
		    
		    src = "data:image/png;base64," + imageToBase64String(src_img, "png");
		    ref = "data:image/png;base64," + imageToBase64String(ref_img, "png");
		    System.out.println("ref: " + ref);
		    
		} catch (IOException e) {
			System.out.println("Image read error");
			e.printStackTrace();
		}
		
		String result = postPrediction(src, ref, "female");
		
		return result;
	}
	
	public String postPrediction(String src_img, String ref_img, String ref_class) {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<PredictionSource> request = new HttpEntity<>(new PredictionSource(src_img, ref_img, ref_class));
		
		PredictionResult result = restTemplate.postForObject(predictionURL + "/predict", request, PredictionResult.class);

		//return quote.toString() + counter.incrementAndGet();
		return result.toString();
	}
	
	public static String imageToBase64String(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            imageString = Base64.getEncoder().encodeToString(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}