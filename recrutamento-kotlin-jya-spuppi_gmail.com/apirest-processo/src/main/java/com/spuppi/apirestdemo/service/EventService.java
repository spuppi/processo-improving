package com.spuppi.apirestdemo.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.spuppi.apirestdemo.model.Event;
import com.spuppi.apirestdemo.resource.EventResource;

public class EventService {

	private static final Logger log = LogManager.getLogger(EventResource.class);

	public Event mapEvent(Object obj) {

		log.info("mapEvent: " + obj);

		Event event = null;

		try {

			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) obj;

			Object parseIssue;

			parseIssue = parser.parse(jsonObject.get("issue").toString());

			JSONObject jsonIssue = (JSONObject) parseIssue;

			event = new Event(Long.parseLong(String.valueOf(
					jsonIssue.get("number"))), 
					String.valueOf(jsonObject.get("action")),
					String.valueOf(jsonIssue.get("title")), 
					String.valueOf(((JSONObject) jsonIssue.get("user")).get("login")),
					String.valueOf(jsonIssue.get("created_at")), 
					String.valueOf(jsonIssue.get("updated_at")), 
					String.valueOf(jsonIssue.get("closed_at")) 
					);

			return event;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return event;
	}
	
	public String encryptPayload(String secret, byte[] content) {
		String result = "";
		Mac sha256_HMAC;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			result = new String(Hex.encodeHex(sha256_HMAC.doFinal(content)));
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
		}catch (IllegalStateException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
