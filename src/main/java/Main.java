import org.apache.log4j.Logger;
import util.SparkUtils;

import static spark.Spark.get;

public class Main {

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(Main.class);
        SparkUtils.createServerWithRequestLog(logger);

        get("/hello", (request, response) -> "world2");
    }

}
