package im.kuka.springboot.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

    private final static Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    public final static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * change obj to json.
     *
     * @param obj
     * @return json string
     */
    public static String toJsonWithException(Object obj) throws IOException {
        return MAPPER.writeValueAsString(obj);
    }

    public static <T> T toObject(String node, Class<T> className) {
        try {
            return MAPPER.readValue(node, className);
        } catch (IOException e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    public static String getJson(Object obj) {

        try {
            return MAPPER.writeValueAsString(obj);
        } catch (IOException e) {
            LOG.warn("failed to write json:" + obj, e);
        }
        return "{}";
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(getJson(s));
    }

    public static JsonNode readJsonResult(String content) {
        try {
            return MAPPER.readValue(content, JsonNode.class);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return MissingNode.getInstance();
    }

    public static JsonNode parserJSONP(String content) {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try {
            content = content.substring(content.indexOf('(') + 1, content.lastIndexOf(')'));
            JsonNode node = JsonUtil.readJsonResult(content);
            return node;
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            return null;
        }
    }

}
