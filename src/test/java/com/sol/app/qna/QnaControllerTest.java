package com.sol.app.qna;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@AutoConfigureMockMvc
class QnaControllerTest {

	// private WebApplicationContext ctx;
	@Autowired
	private MockMvc mockMvc;

//	@Test
//	void test() {
//		//this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
//	}

	@Test
	void getListTest() throws Exception{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String> ();
		params.add("page", "1");
		params.add("kind", "k1");
		params.add("search", "2");
		
		mockMvc.perform
		(
				get("/qna/list")
				.formFields(params)
		)
		.andDo(print());
	}

	@Test
	public void getDetailTest() throws Exception {
		mockMvc.perform
		(
				get("/qna/detail")
				.param("boardNum", "110")
		)
		.andExpect(status().isOk())
		.andDo(print());
	}
	
//	@Test
//	public void addTest() throws Exception {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String> ();
//		params.add("boardTitle", "mockMvcTest boardTitle2");
//		params.add("boardWriter", "mockMvcTest boardWriter2");
//		params.add("boardContents", "mockMvcTest boardContents2");
//		
//		mockMvc.perform
//		(
//			post("/qna/add")
//			.formFields(params)
//		)
//		.andDo(print());
//		
//		mockMvc.perform
//		(
//			post("/qna/add")
//			.params(params)
//		)
//		.andDo(print());
//	}
}
