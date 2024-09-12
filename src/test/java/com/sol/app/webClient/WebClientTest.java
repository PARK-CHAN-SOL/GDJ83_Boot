package com.sol.app.webClient;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@Slf4j
public class WebClientTest {
	
	@Test
	void webClientListTest() {
		WebClient webClient = WebClient.builder()
								.baseUrl("https://jsonplaceholder.typicode.com/")
								.build()
								;
		
		Flux<PostVO> res = webClient
								.get()
								.uri("posts")
								.retrieve()
								.bodyToFlux(PostVO.class)
								;
		
		List<PostVO> list = res.collectList().block();
		
		res.toStream().toList();
		
		int i = 0;
		
//		작동 안함
//		while(res.hasElements().block() || i != 100) {
//			log.error("postVO: {}", res.blockFirst());
//			i++;
//		}
		
//		작동함
		log.info(list.toString());
		log.info("list.size(): {}", list.size());
	}
	
	//@Test
	void webClientTest() {
		WebClient webClient = WebClient.builder()
								.baseUrl("https://jsonplaceholder.typicode.com/")
								.build()
								;
		
		Mono<PostVO> res = webClient
								.get()
								.uri("posts/1")
								.retrieve()
								.bodyToMono(PostVO.class)
								;
		
		PostVO postVO = res.block();
		
		log.info(postVO.toString());
	}
	
	//@Test
	void test1() throws Exception {
		//RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		
		//parameter 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); 
		params.add("postId", "1");
		
		HttpHeaders header = new HttpHeaders();
		
		header.add("", "");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, null);
		
		//요청 전송 응답 처리
		List<PostVO> res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", List.class);
		
		log.info("Result: {}", res.size());
		log.info("Result: {}", res.get(0));
		log.info("Result: {}", res.get(99));
		
	}
	
}
