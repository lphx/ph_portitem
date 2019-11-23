package cn.phlos.port.item.sql.service;

public interface SqlService {


    String parse(String[] colnames, String[] colTypes, int[] colSizes);
    void importPackage(StringBuffer sb,String tablename);
    void init(StringBuffer sb,String tablename);
    void add(StringBuffer sb,String tablename);
    void update(StringBuffer sb,String tablename);
    public void delete(StringBuffer sb,String tablename);

    public  void findById(StringBuffer sb,String tablename);


   void findAllList(StringBuffer sb,String tablename);

}
