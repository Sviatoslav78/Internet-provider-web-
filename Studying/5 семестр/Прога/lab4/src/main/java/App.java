import org.apache.log4j.Logger;

public class App {

    public static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        String url = "/admin/edit-tariffs/delete-tariff";
//        for (String s :url.split("/")) {
//            System.out.println(s);
//        }
        System.out.println(url.split("/")[1]);

    }
}
