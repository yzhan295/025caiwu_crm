package com.ifinance.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
public class RSA{
     
    public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
     
    /**
    * RSA签名
    * @param content 待签名数据
    * @param privateKey 商户私钥
    * @param input_charset 编码格式
    * @return 签名值
    */
    public static String sign(String content, String privateKey, String input_charset)
    {
        try
        {
            PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey) );
            KeyFactory keyf                 = KeyFactory.getInstance("RSA");
            PrivateKey priKey               = keyf.generatePrivate(priPKCS8);
 
            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);
 
            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );
 
            byte[] signed = signature.sign();
             
            return Base64.encodeBase64String(signed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         
        return null;
    }
     
//    /**
//    * RSA验签名检查
//    * @param content 待签名数据
//    * @param sign 签名值
//    * @param ali_public_key 支付宝公钥
//    * @param input_charset 编码格式
//    * @return 布尔值
//    */
//    public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
//    {
//        try
//        {
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] encodedKey = Base64.decode(ali_public_key);
//            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
// 
//         
//            java.security.Signature signature = java.security.Signature
//            .getInstance(SIGN_ALGORITHMS);
//         
//            signature.initVerify(pubKey);
//            signature.update( content.getBytes(input_charset) );
//         
//            boolean bverify = signature.verify( Base64.decode(sign) );
//            return bverify;
//             
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//         
//        return false;
//    }
//     
//    /**
//    * 解密
//    * @param content 密文
//    * @param private_key 商户私钥
//    * @param input_charset 编码格式
//    * @return 解密后的字符串
//    */
//    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
//        PrivateKey prikey = getPrivateKey(private_key);
// 
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, prikey);
// 
//        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
//        ByteArrayOutputStream writer = new ByteArrayOutputStream();
//        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
//        byte[] buf = new byte[128];
//        int bufl;
// 
//        while ((bufl = ins.read(buf)) != -1) {
//            byte[] block = null;
// 
//            if (buf.length == bufl) {
//                block = buf;
//            } else {
//                block = new byte[bufl];
//                for (int i = 0; i < bufl; i++) {
//                    block[i] = buf[i];
//                }
//            }
// 
//            writer.write(cipher.doFinal(block));
//        }
// 
//        return new String(writer.toByteArray(), input_charset);
//    }
// 
//     
//    /**
//    * 得到私钥
//    * @param key 密钥字符串（经过base64编码）
//    * @throws Exception
//    */
//    public static PrivateKey getPrivateKey(String key) throws Exception {
// 
//        byte[] keyBytes;
//         
//        keyBytes = Base64.decode(key);
//         
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//         
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//         
//        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//         
//        return privateKey;
//    }
    
//    public static void main(String[] args)
//    {
//    	String bb =sign("appId=463&userId=11223344", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOCEDkV5lL8x5g70XSvCdzbDKtd8Pg5jI4eOiBTgFEGacdhBA0d4WIs+hlIGT+EXt4VtESUA8ZzjDZEmSdw5x6kBbvFeKvD19D76i8XU7KdUsQRR7RuQT5UO2QXPUYfz4NxR22g1ncmtMFt+63tAp9GA5RSk/4c0GzpMBikDbSinAgMBAAECgYBoNYM2Rhd/oxaIbG3uiMWyumXhPP7EyMlN+x84qUVdqeUbnhl6i+Z1QyEvY1SiQtLg8F3b0hNTYaQDckUoHf2Ims9mp4t0Y42Og4Me42Ka20oSuklzUg2428k8wb3vQ1OR1lfr8DDLsXyArRDOV31XSv4jN7yf5+FO4dcPDlyyEQJBAPVSlehE6pSuM/qfWadiP7dfWx47wFvvq8TKUqQAwuhfSk9PI2+h+SgkCMG6roH1DCajUBJih0FI17/bkMSH+2kCQQDqSaOEWx9xMDY7JgZ6KYucGWsF5H5yGIEHuSyM5AvJMOuhZeYPpBKMMy1zyLW3gWfb1MxkXIVgbWOW/31pn9GPAkEA1a867ldhMWLP2eUFVbxJnbNMjfgj5LDOIJrPAxW6sGeYNZ7MUgd3HJTOn9i4+m/1Ro6IR4tVHvORIgfhgJ6lyQJBAIdsjWIiMeiBT7ec4T2X6iUO5DgBvb1BwMOwG7pMzys6ZdljRzpfQoFby7gTmkY6PMhrFsIkYMw+LsTHZby6Lq0CQBnShlyin+VmDh1Mm5jCiNLA+FFW3YavFTAvWsJzYinnv3ji5F/o8pYeeT/FCmxL5mgP0aw8DuC89bOsI5uh2kk=", "utf-8");
//    	System.out.println(bb);
//    	
//    }
}