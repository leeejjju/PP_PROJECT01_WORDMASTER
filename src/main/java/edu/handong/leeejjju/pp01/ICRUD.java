package edu.handong.leeejjju.pp01;

//CRUD를 위한 인터페이스. 인텉페이스는 이름 앞에 I를 붙이는게 국룰이라구 해요
public interface ICRUD {

    public Object add(); //추가될 데이터를 리턴
    public int update(Object obj);
    public int delete(Object obj);
    public void selectOne(int id);

}
