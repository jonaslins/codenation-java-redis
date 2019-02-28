package challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class RestaurantIntegrationTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void test() {		
		double x = -73.9506635;
		double y = 40.7858944;

		ResponseEntity<String> response = restTemplate.getForEntity("/restaurants/findInNeighborhood?x={x}&y={y}", String.class, x, y);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}
	
	@Before
	public void before() throws Exception {
		mongoTemplate.dropCollection("restaurant");
		mongoTemplate.dropCollection("neighborhood");
		
		try (BufferedReader br = getBufferedReader("restaurants.json")) {
			ArrayList<String> rows = new ArrayList<>();
			
			String line;
			while ((line = br.readLine()) != null) {
				rows.add(line);
			}
			
			mongoTemplate.insert(rows, "restaurant");
		}	
		
		try (BufferedReader br = getBufferedReader("neighborhoods.json")) {
			ArrayList<String> rows = new ArrayList<>();
			
			String line;
			while ((line = br.readLine()) != null) {
				rows.add(line);
			}
			
			mongoTemplate.insert(rows, "neighborhood");
		}		
	}
	
	private BufferedReader getBufferedReader(String file) throws FileNotFoundException {
		return new BufferedReader(new FileReader(getClass().getClassLoader().getResource(file).getPath()));
	}

}
