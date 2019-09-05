/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

/**
 *
 * @author labfe
 */
public class SQLUtils {
    
    public static java.sql.Timestamp getCurrentTimeStamp(){
        java.util.Date dt = new java.util.Date(); 
        return new java.sql.Timestamp(dt.getTime());
    }
    
    public static String decodeBinary(InputStream is) {
        //BigInteger bi = new BigInteger(bytes);
        //String decoded_binary = bi.toString(2);
        String decoded_binary = "";
        try {
            decoded_binary = new String(readAllBytes(is));
        } catch (IOException ex) {
            Logger.getLogger(SQLUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("[DECODED BINARY UUID]: " + decoded_binary);
        return decoded_binary;
    }
    
    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
    final int bufLen = 4 * 0x400; // 4KB
    byte[] buf = new byte[bufLen];
    int readLen;
    IOException exception = null;

    try {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            while ((readLen = inputStream.read(buf, 0, bufLen)) != -1)
                outputStream.write(buf, 0, readLen);

            return outputStream.toByteArray();
        }
    } catch (IOException e) {
        exception = e;
        throw e;
    } finally {
        if (exception == null) inputStream.close();
        else try {
            inputStream.close();
        } catch (IOException e) {
            exception.addSuppressed(e);
        }
    }
}
    
}
