package ink.whi.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    String title;
    long beginTime;
    long endTime;
    String desc;
}
