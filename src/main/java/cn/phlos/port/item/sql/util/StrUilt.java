package cn.phlos.port.item.sql.util;

public class StrUilt {


    public static String toOneLower(String str){
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }

    /**
     * 判断,如t_user_name,去掉t改为UserName
     */
    public static String humpName(String tableName) {

        String aa[] = tableName.split("_");
        String letter=aa[0];
        for(int i=1;i<aa.length;i++){

            letter+=aa[i].substring(0,1).toUpperCase()+aa[i].substring(1);
        }
        return letter;
    }


}
