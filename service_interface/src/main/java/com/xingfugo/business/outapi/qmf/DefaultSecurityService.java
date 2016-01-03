package com.xingfugo.business.outapi.qmf;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class DefaultSecurityService {

	private String signKeyModHex;
	private String signKeyExpHex;

	private String verifyKeyModHex;
	private String verifyKeyExpHex;

	private Signature signature, verifier;

	public DefaultSecurityService() {

	}

	public String sign(String data) {
		try {
			initSignKey();
			signature.update(data.getBytes("UTF-8"));
			byte[] sign = signature.sign();
			return byteArray2HexString(sign);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public boolean verify(String data, String sign) {
		try {
			initVerifyKey();
			byte[] dataBytes = data.getBytes("UTF-8");
			verifier.update(dataBytes);
			return verifier.verify(hexString2ByteArray(sign));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private void initSignKey() throws Exception {
		if (signature == null) {
			BigInteger mod = new BigInteger(signKeyModHex, 16);
			BigInteger exp = new BigInteger(signKeyExpHex, 16);
			RSAPrivateKeySpec spec = new RSAPrivateKeySpec(mod, exp);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey signKey = keyFactory.generatePrivate(spec);
			signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(signKey);
		}
	}

	private void initVerifyKey() throws Exception {
		if (verifier == null) {
			RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(
					verifyKeyModHex, 16), new BigInteger(verifyKeyExpHex, 16));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey verifyKey = keyFactory.generatePublic(spec);
			verifier = Signature.getInstance("SHA1withRSA");
			verifier.initVerify(verifyKey);
		}
	}

	public String getSignKeyModHex() {
		return signKeyModHex;
	}

	public void setSignKeyModHex(String signKeyMod) {
		this.signKeyModHex = signKeyMod;
	}

	public String getSignKeyExpHex() {
		return signKeyExpHex;
	}

	public void setSignKeyExpHex(String signKeyExp) {
		this.signKeyExpHex = signKeyExp;
	}

	public String getVerifyKeyModHex() {
		return verifyKeyModHex;
	}

	public void setVerifyKeyModHex(String verifyKeyModHex) {
		this.verifyKeyModHex = verifyKeyModHex;
	}

	public String getVerifyKeyExpHex() {
		return verifyKeyExpHex;
	}

	public void setVerifyKeyExpHex(String verifyKeyExpHex) {
		this.verifyKeyExpHex = verifyKeyExpHex;
	}

	public static String byteArray2HexString(byte[] arr) {
		StringBuilder sbd = new StringBuilder();
		for (byte b : arr) {
			String tmp = Integer.toHexString(0xFF & b);
			if (tmp.length() < 2)
				tmp = "0" + tmp;
			sbd.append(tmp);
		}
		return sbd.toString();
	}

	/**
	 * 十六进制 转换 byte[]
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] hexString2ByteArray(String hexStr) {
		if (hexStr == null)
			return null;
		hexStr = hexStr.replaceAll(" ", "");
		if (hexStr.length() % 2 != 0) {
			return null;
		}
		byte[] data = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			char hc = hexStr.charAt(2 * i);
			char lc = hexStr.charAt(2 * i + 1);
			byte hb = hexChar2Byte(hc);
			byte lb = hexChar2Byte(lc);
			if (hb < 0 || lb < 0) {
				return null;
			}
			int n = hb << 4;
			data[i] = (byte) (n + lb);
		}
		return data;
	}

	public static byte hexChar2Byte(char c) {
		if (c >= '0' && c <= '9')
			return (byte) (c - '0');
		if (c >= 'a' && c <= 'f')
			return (byte) (c - 'a' + 10);
		if (c >= 'A' && c <= 'F')
			return (byte) (c - 'A' + 10);
		return -1;
	}

	public static void main(String args[]) {
		/*
		 * String singKeyMod=
		 * "83beb97d3aa44b696b2e1633d6d6fe5ec2b86d2d8ba8437c5c4bcac0530b7d50f03af18dee28f7ebd8859d7063254f3751c1c3594a6146e430ea442489b8fb46dc38c34f42241b0783044b459ce8b377006bc7b1a3b58f41ad772ff65846f4946e9d68e1d78564f89b70b2c713c0e6efbb03100e317eb3214d9ed072fbee3a07"
		 * ; String singKeyExp=
		 * "1e4c5e9c4e403a97a3ee956c969c1b23efe43a379f46b33e867b67c59353b11e4c21422c41f96a0af360c7347198c2ff15ee59decf1c50116aae75bd716ef95a9dffd055bc872dc840a53f1d8fdbf08430efa394f8fe7ffc708ccbf4b9d46f6c833a415e57abd811d4b2b1aee64f59e1b87a74986fc7bd04514f924b5550a901"
		 * ; DefaultSecurityService ss = new DefaultSecurityService();
		 * //设置签名的商户私钥，验签的银商公钥 ss.setSignKeyModHex(singKeyMod);//签名私钥Mod
		 * ss.setSignKeyExpHex(singKeyExp);//签名私钥Exp String data =
		 * "180804020130827jf1377599404256296028124NoticePay189800009399000199999999http://www.99ums.com/test/ordernotify/333测试"
		 * ; data=
		 * "090820020130830jf1377826520630466093202NoticePay189800009399000199999999http://www.99ums.com/test/ordernotify/333test"
		 * ; String sign = ss.sign(data); System.out.println("sign:"+sign);
		 */

		/*
		String singKeyMod = "80b69894620f0f252c8b2a7357f291cc7fe5498d8d5fbe4b61739d625f548c1bd101d9a5df14f9714c9476ae4bf67670663d1537f306568836ffe6d4d862e588971237708b4dcd73c92a356406bb346e8e88cb06f1e9392218e9ea25144ba185629ec148caca236748cb0eb74e4051bf68b50c1625ec94d31143449f32dd5df9";
		String singKeyExp = "1008af112cf5fb49023783de4282712f2c6f7fae26825ace412189e58b4299018e13d0ed1cd11c11c51c697c64f21ae6fd95766335df00408fb96923f2a0bf3f0ae7bc88c59a7562b16d8ada64c9dd6d6f91a4146844a702b4d9d8c4f2bcbf76fe03decd5c065365238a1c275da1040f80004bb41485ade8609566e8f3518201";
		String verifyKeyExp = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001";
		String verfiyKeyMod = "80b69894620f0f252c8b2a7357f291cc7fe5498d8d5fbe4b61739d625f548c1bd101d9a5df14f9714c9476ae4bf67670663d1537f306568836ffe6d4d862e588971237708b4dcd73c92a356406bb346e8e88cb06f1e9392218e9ea25144ba185629ec148caca236748cb0eb74e4051bf68b50c1625ec94d31143449f32dd5df9";

		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		// http://116.228.21.170/qmfweb/testXYBank?channelId=100001&amp;customizeId=1101&amp;mobileNum=13859091384&amp;signature=a0e8d5899595a1ad67063f8d49aa6d612ef3c0ed969a8d7b2db7682a7259429970c2e032245ce5e428cb22582f68cfb200591bc50cfc05b7b110c39ffd35df9f476d5fb716c944a0918587fdfe0dda12fcc7b11fd309a8a958b4ef575010bcda7997e41e431a027d5723cc3b4eca59d771e794283ef92f2248219d8d938fd6ef'
		ss.setSignKeyModHex(singKeyMod);// 签名私钥Mod
		ss.setSignKeyExpHex(singKeyExp);// 签名私钥Exp
		ss.setVerifyKeyModHex(verfiyKeyMod);
		ss.setVerifyKeyExpHex(verifyKeyExp);

		String sign = "a0e8d5899595a1ad67063f8d49aa6d612ef3c0ed969a8d7b2db7682a7259429970c2e032245ce5e428cb22582f68cfb200591bc50cfc05b7b110c39ffd35df9f476d5fb716c944a0918587fdfe0dda12fcc7b11fd309a8a958b4ef575010bcda7997e41e431a027d5723cc3b4eca59d771e794283ef92f2248219d8d938fd6ef";
		 sign="82ec75ff3b572a84a4cc806409045ba9b7b200f2b555b8313ec200ea9eeef2f0b2db20af0f4432cb1f7bee48150da814afd376d310630bb32e44fde2f66597775eaeea9369b34c7317b981cb7c85c1cf61769907fc96ce8485457afbc3509596670ebb6655221962e6904b6c186b74038b65d32e73cdbc5680dae12b4ab6bdbb";
		String data = "100001110118616367797";

		System.out.println("sign:" + ss.sign(data));
		boolean result = ss.verify(data, sign);
		System.out.println("result:" + result);
		
		//System.out.println(DefaultSecurityService.getSign(data));
		 
		 */
		
		String ss = DefaultSecurityService.getSign("100005110518801005267");
		System.out.println(ss);
		
	}
	
	public static String getSign(String data){
		String singKeyMod = "80b69894620f0f252c8b2a7357f291cc7fe5498d8d5fbe4b61739d625f548c1bd101d9a5df14f9714c9476ae4bf67670663d1537f306568836ffe6d4d862e588971237708b4dcd73c92a356406bb346e8e88cb06f1e9392218e9ea25144ba185629ec148caca236748cb0eb74e4051bf68b50c1625ec94d31143449f32dd5df9";
		String singKeyExp = "1008af112cf5fb49023783de4282712f2c6f7fae26825ace412189e58b4299018e13d0ed1cd11c11c51c697c64f21ae6fd95766335df00408fb96923f2a0bf3f0ae7bc88c59a7562b16d8ada64c9dd6d6f91a4146844a702b4d9d8c4f2bcbf76fe03decd5c065365238a1c275da1040f80004bb41485ade8609566e8f3518201";
		String verifyKeyExp = "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000010001";
		String verfiyKeyMod = "80b69894620f0f252c8b2a7357f291cc7fe5498d8d5fbe4b61739d625f548c1bd101d9a5df14f9714c9476ae4bf67670663d1537f306568836ffe6d4d862e588971237708b4dcd73c92a356406bb346e8e88cb06f1e9392218e9ea25144ba185629ec148caca236748cb0eb74e4051bf68b50c1625ec94d31143449f32dd5df9";

		DefaultSecurityService ss = new DefaultSecurityService();
		// 设置签名的商户私钥，验签的银商公钥
		ss.setSignKeyModHex(singKeyMod);// 签名私钥Mod
		ss.setSignKeyExpHex(singKeyExp);// 签名私钥Exp
		ss.setVerifyKeyModHex(verfiyKeyMod);
		ss.setVerifyKeyExpHex(verifyKeyExp);
		
		return ss.sign(data);
	}
	
}
