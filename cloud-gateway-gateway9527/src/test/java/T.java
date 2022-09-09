import java.time.ZonedDateTime;

public class T {
    public static void main(String[] args) {
        System.out.println("xx");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // 用指定时区获取当前时间
//      ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime);
//      2022-09-08T11:55:29.877+08:00[Asia/Shanghai]

    }
}
