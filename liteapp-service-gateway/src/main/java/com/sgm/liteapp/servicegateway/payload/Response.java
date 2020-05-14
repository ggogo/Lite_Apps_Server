package com.sgm.liteapp.servicegateway.payload;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class Response {

	public static Mono<Void> error(ServerWebExchange exchange, HttpStatus status, String error) {
		ServerHttpResponse response = exchange.getResponse();
		byte[] msg = null;
		try {
			msg = JSON.buildUTF8(new Fault(status.value(), error));
		} catch (Exception e) {
			msg = ("{\"responseCode\":" + status.value() + ", \"error\":\"" + error + "\",\"timestamp\":"
					+ System.currentTimeMillis() + "}").getBytes();
		}
		DataBuffer buffer = response.bufferFactory().wrap(msg);
		response.setStatusCode(status);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}

	public static Mono<Void> error(ServerWebExchange exchange, HttpStatus status, String error, String errorMsg) {
		ServerHttpResponse response = exchange.getResponse();
		byte[] msg = null;
		try {
			msg = JSON.buildUTF8(new Fault(status.value(), error, errorMsg));
		} catch (Exception e) {
			msg = ("{\"responseCode\":" + status.value() + ", \"error\":\"" + error + "\", \"message\":\"" + errorMsg
					+ "\", \"timestamp\":" + System.currentTimeMillis() + "}").getBytes();
		}
		DataBuffer buffer = response.bufferFactory().wrap(msg);
		response.setStatusCode(status);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}

	public static Mono<Void> error(ServerWebExchange exchange, HttpStatus status) {
		return error(exchange, status, status.getReasonPhrase());
	}

	public static Mono<Void> ok(ServerWebExchange exchange, Object obj) {
		ServerHttpResponse response = exchange.getResponse();
		byte[] msg = null;
		try {
			msg = JSON.buildUTF8(obj);
		} catch (Exception e) {
			msg = "{}".getBytes();
		}
		DataBuffer buffer = response.bufferFactory().wrap(msg);
		response.setStatusCode(HttpStatus.OK);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}

	public static Mono<Void> image(ServerWebExchange exchange, String type, byte[] content) {
		ServerHttpResponse response = exchange.getResponse();
		DataBuffer buffer = response.bufferFactory().wrap(content);
		response.setStatusCode(HttpStatus.OK);
		response.getHeaders().add("Content-Type", " image/" + type);
		return response.writeWith(Mono.just(buffer));
	}
}
