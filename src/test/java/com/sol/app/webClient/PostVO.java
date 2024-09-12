package com.sol.app.webClient;

import lombok.Data;

@Data
public class PostVO {
	private Long userId; 
	private Long postId;
	private Long id;
	private String email;
	private String body;
	private String title;
}
