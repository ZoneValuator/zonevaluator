package fr.esgi.zonevaluator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = ZonevaluatorApplication.class)

@AutoConfigureMockMvc
class ZonevaluatorApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGeneratePdfByLocationEndpoint() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/generatePdfByLocation")
							.param("latitude", "40.7128")
							.param("longitude", "-74.0060")
							.param("rayon", "10")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andDo(print());
	}

	@Test
	void testGeneratePdfByLocationEndpointWithoutLatitude() throws Exception {
			mockMvc.perform(MockMvcRequestBuilders.get("/generatePdfByLocation")
							.param("longitude", "-74.0060")
							.param("rayon", "10")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isBadRequest())
					.andDo(print());
	}
}
