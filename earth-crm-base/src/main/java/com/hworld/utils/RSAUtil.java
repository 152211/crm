package com.hworld.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class RSAUtil {

    private static final String ALGORITHM = "RSA";

    //加密或解密长度过长会报错, 提示"最大长度不超过xxx", 所以需要分段加解密
    private static final int MAX_ENCRYPT_BLOCK = 117;//Cipher一次加密最大字节长度
    private static final int MAX_DECRYPT_BLOCK = 128;//Cipher一次解密最大字节长度
    public static final String KEY_TYPE_PRIVATE = "private";
    public static final String KEY_TYPE_PUBLIC = "public";

    private RSAUtil(){}

    /**
     * 随机生成 KeyPair
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair genKeyPair() throws NoSuchAlgorithmException {
        //根据RSA算法获取KeyPairGenerator, 并初始化
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(1024,new SecureRandom());

        //生成KeyPair,keyPair中存储了一对秘钥
        //通过KeyPair获取公钥和秘钥之后结合具体业务进行操作,比如将秘钥存入数据库等。。
        //可以参考main方法中的示例将秘钥转换为字符串
        return keyPairGenerator.generateKeyPair();
    }


    /**
     * 默认公钥加密
     * @param str
     * @param keyStr
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String keyStr) throws Exception{
        return encrypt(str,keyStr,KEY_TYPE_PUBLIC);
    }

    /**
     * 默认私钥解密
     * @param str
     * @param keyStr
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String keyStr) throws Exception{
        return decrypt(str,keyStr,KEY_TYPE_PRIVATE);
    }


    /**
     * 加密
     * @param str
     * @param keyStr
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String keyStr, String keyType) throws Exception {

        //初始化加密Cipher
        Cipher cipher = getCipher(str,keyStr,keyType,Cipher.ENCRYPT_MODE);

        byte[] strByte = str.getBytes(StandardCharsets.UTF_8);
        //分段加密
        byte[] resultByte = segment(cipher,strByte,MAX_ENCRYPT_BLOCK);

        return Base64Utils.encodeToString(resultByte);
    }



    /**
     * 解密
     * @param str
     * @param keyStr
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String keyStr, String keyType) throws Exception{

        //初始化解密Cipher
        Cipher cipher = getCipher(str,keyStr,keyType,Cipher.DECRYPT_MODE);

        byte[] strByte = Base64Utils.decodeFromString(str);
        //分段解密
        byte[] resultByte = segment(cipher,strByte,MAX_DECRYPT_BLOCK);

        return StringUtils.toEncodedString(resultByte,StandardCharsets.UTF_8);
    }

    /**
     * 获取cipher
     * @param str: 需要加解密的字符串
     * @param keyStr: 密钥字符串
     * @param keyType: 密钥类型
     * @param cipherMode: 加解密类型
     * @return
     * @throws Exception
     */
    private static Cipher getCipher(String str,String keyStr, String keyType, int cipherMode) throws Exception{
        byte[] keyByte = Base64Utils.decodeFromString(keyStr);
        Key key = null;
        switch (keyType){
            case KEY_TYPE_PRIVATE:
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyByte);
                key = KeyFactory.getInstance(ALGORITHM).generatePrivate(pkcs8EncodedKeySpec);
                break;
            case KEY_TYPE_PUBLIC:
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyByte);
                key = KeyFactory.getInstance(ALGORITHM).generatePublic(x509EncodedKeySpec);
        }

        //初始化解密Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(cipherMode,key);
        return cipher;
    }

    /**
     * 分段加解密
     * @param cipher
     * @param strByte
     * @param maxBlock
     * @return
     */
    private static byte[] segment(Cipher cipher, byte[] strByte, int maxBlock) throws Exception {
        int length = strByte.length;
        int offset = 0;
        byte[] resultByte = {};
        byte[] cache = {};
        while (length - offset > 0){
            if (length - offset > maxBlock){
                //剩余长度大于最大长度时,则按加解密长度为最大长度,并设置偏移量offset
                cache = cipher.doFinal(strByte,offset,maxBlock);
                offset += maxBlock;
            }else {
                //运行到此处代表最后一段加解密,完成后整段加解密完成
                cache = cipher.doFinal(strByte,offset,length - offset);
                offset = length;
            }

            //复制resultByte到新的数组, 数组长度为resultByte和cache的长度之和
            resultByte = Arrays.copyOf(resultByte, resultByte.length + cache.length);
            //将新的加解密字节添加到resultByte的结尾
            System.arraycopy(cache,0,resultByte,resultByte.length-cache.length, cache.length);
        }
        return resultByte;
    }

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = genKeyPair(); //获取KeyPair
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); //获取公钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); //获取私钥
        String publicKeyStr = Base64Utils.encodeToString(publicKey.getEncoded());
        String privateKeyStr = Base64Utils.encodeToString(privateKey.getEncoded());
        System.out.println("公钥: "+publicKeyStr);
        System.out.println("私钥: "+privateKeyStr);

        String str = "{\"userName\":\"caoyang\",\"userId\":\"1000\",\"time\":\"1667531479\",\"email\":\"caoyang029@huazhu.com\",\"profId\":\"I300012585521551445522415520144552245\",\"userName1\":\"caoyang\",\"userId1\":\"1000\",\"time1\":\"1667531479\",\"email1\":\"caoyang029@huazhu.com\",\"profId1\":\"I300012585521551445522415520144552245\",\"userName1\":\"caoyang\",\"userId1\":\"1000\",\"time1\":\"1667531479\",\"email1\":\"caoyang029@huazhu.com\",\"profId1\":\"I300012585521551445522415520144552245\",\"userName2\":\"caoyang\",\"userId2\":\"1000\",\"time2\":\"1667531479\",\"email2\":\"caoyang029@huazhu.com\",\"profId2\":\"I300012585521551445522415520144552245\",\"userName2\":\"caoyang\",\"userId11\":\"1000\",\"time11\":\"1667531479\",\"email11\":\"caoyang029@huazhu.com\",\"profId11\":\"I300012585521551445522415520144552245\",\"userName11\":\"caoyang\",\"userId11\":\"1000\",\"time11\":\"1667531479\",\"email11\":\"caoyang029@huazhu.com\",\"profId11\":\"I300012585521551445522415520144552245\"}";
        //公钥加密,私钥解密
        System.out.println("***************** 公钥加密,私钥解密 ****************");
        String encryptByPublicStr = encrypt(str,publicKeyStr);
        System.out.println("公钥加密："+encryptByPublicStr);
        System.out.println("私钥解密："+decrypt(encryptByPublicStr,privateKeyStr));
        System.out.println("");
        //私钥加密,公钥解密
        System.out.println("***************** 私钥加密,公钥解密 ****************");
        String encryptByPrivateStr = encrypt(str,privateKeyStr,KEY_TYPE_PRIVATE);
        System.out.println("私钥加密："+encryptByPrivateStr);
        System.out.println("公钥解密："+decrypt(encryptByPrivateStr,publicKeyStr,KEY_TYPE_PUBLIC));
    }
}

