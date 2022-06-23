package pl.polsl.hrservice.raport.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.Normalizer;

/**
 * Created by marcinpiela on 02/04/2021.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpHeadersUtil {
    public static String getContentDisposition(final String filename) throws UnsupportedEncodingException {
        String filenameAscii = Normalizer.normalize(filename, Normalizer.Form.NFD).replaceAll("[^\\x00-\\x7F]", "");
        String filenameUtf8 = URLEncoder.encode(filename, "utf-8").replace("+", "%20");
        return StringUtils.join("attachment; filename=\"", filenameAscii, "\"; filename*=UTF-8''", filenameUtf8);
    }
}
