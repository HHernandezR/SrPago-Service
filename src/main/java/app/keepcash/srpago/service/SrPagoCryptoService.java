package app.keepcash.srpago.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.keepcash.srpago.config.SecretsInit;
import app.keepcash.srpago.model.payment.Request;

@Component
public class SrPagoCryptoService {
	
	@Autowired
	private SecretsInit secretsInit;
	
	private static final String ALGORITHM = "RSA";
	
	private static final String ALGORITHM_TMP = "AES";

	public SrPagoCryptoService() {
		super();
	}

	public Request encryptData(String data) {
		Request request = new Request();
		try {
			request = this.objectCryptData(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	private Request objectCryptData(String msg) {
		Request request = new Request();
		try {
			String plainKey = this.generateKey();
			byte[] msgCrypt = this.cryptoData(plainKey, msg);
			byte[] keyCrypt = this.cryptoKey(plainKey);

			byte[] encodedKey = Base64.encodeBase64(keyCrypt);
		    byte[] encodedMessage = Base64.encodeBase64(msgCrypt);

			request.setData(new String(encodedMessage, "UTF-8"));
			request.setKey(new String(encodedKey, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private byte[] cryptoData(String encryptionKey, String data) {
		byte[] cipherText = null;
		try {
			SecretKeySpec keySpecification = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), ALGORITHM_TMP);
			Cipher cipher = Cipher.getInstance(ALGORITHM_TMP);
			int newMaxKeyLength = Cipher.getMaxAllowedKeyLength(ALGORITHM_TMP);
			if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
				Class clas = Class.forName("javax.crypto.CryptoAllPermissionCollection");
				Constructor constructor = clas.getDeclaredConstructor();
				constructor.setAccessible(true);
				Object allPermissionCollection = constructor.newInstance();
				Field field = clas.getDeclaredField("all_allowed");
				field.setAccessible(true);
				field.setBoolean(allPermissionCollection, true);

				clas = Class.forName("javax.crypto.CryptoPermissions");
				constructor = clas.getDeclaredConstructor();
				constructor.setAccessible(true);
				Object allPermissions = constructor.newInstance();
				field = clas.getDeclaredField("perms");
				field.setAccessible(true);
				((Map) field.get(allPermissions)).put("*", allPermissionCollection);

				clas = Class.forName("javax.crypto.JceSecurityManager");
				field = clas.getDeclaredField("defaultPolicy");
				field.setAccessible(true);
				Field mf = Field.class.getDeclaredField("modifiers");
				mf.setAccessible(true);
				mf.setInt(field, field.getModifiers() & ~Modifier.FINAL);
				field.set(null, allPermissions);
				newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
			}
			if (newMaxKeyLength < 256) {
				String errorString = "Failed overriding key-length permissions";
				throw new RuntimeException(errorString);
			}
			cipher.init(Cipher.ENCRYPT_MODE, keySpecification);
			cipherText = cipher.doFinal(data.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

	private String generateKey() {
		String key = "";
		try {
			SecureRandom random = new SecureRandom();
			key = new BigInteger(160, random).toString(32);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	private byte[] cryptoKey(String key) {
		byte[] cipherText = null;
		try {
			PublicKey publicKey = this.getPublicKey();
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			cipherText = cipher.doFinal(key.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}
	
	private PublicKey getPublicKey() {
		RSAPublicKey pubKey = null;
		try {
			String pubKeySrPago = this.secretsInit.getSrPagoSecrets().getPublickey();
			byte[] pubKeySavedDecoded = Base64.decodeBase64(pubKeySrPago.getBytes());
			String publicKeyPEM = new String(pubKeySavedDecoded, "UTF-8");
			publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
			publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
			byte[] encoded = Base64.decodeBase64(publicKeyPEM.getBytes());
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			X509EncodedKeySpec spec = new X509EncodedKeySpec(encoded);
			pubKey = (RSAPublicKey) keyFactory.generatePublic(spec);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pubKey;
	}

}
