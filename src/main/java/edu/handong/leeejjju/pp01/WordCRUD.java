package edu.handong.leeejjju.pp01;
import java.util.ArrayList;
import java.util.Scanner;

//ICRUD interface의 구현체
public class WordCRUD implements ICRUD{

    private ArrayList<Word> list;
    private Scanner in;

    private int id;
    private int level;
    private String word;
    private String meaning;


    public WordCRUD() {
        list = new ArrayList<>();
        in = new Scanner(System.in);
    }

    //입력받기
    /*
    난이도(1,2,3) & 새 단어 입력 : 1 driveway
    뜻 입력 : 차고 진입로
    새 단어가 단어장에 추가되었습니다 !!!
     */
    @Override
    public Word add() {

        //id는 리스트의 마지막... 아니이거ㅓ 중간거 삭ㅈ하믄 어케할라구??
        //d일단 id는 리스트 마지막놈 id에서 +1해서 해봅겠읍니다..
        if(list.isEmpty()) id = 1;
        else id = list.get(list.size()-1).getId()+1;

        System.out.print("난이도(1,2,3) & 새 단어 입력 : ");
        level = in.nextInt();
        word = in.nextLine();
        System.out.print("뜻 입력 : ");
        meaning = in.nextLine();
        Word myWord = new Word(id, level, word, meaning);
        return myWord;
    }

    public void addWord(){

        list.add(this.add());
        System.out.println("\n새 단어가 단어장에 추가되었습니다 !!!\n");

    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }

    /*
    --------------------------------
    1 * driveway 차고 진입로
    2 ** graceful 우아한
    --------------------------------
     */
    public void listAll(){
        System.out.println("\n------------------------------------------------");
        for(int i = 0; i < list.size(); i++){
            System.out.println((i+1) + list.get(i).toString());
        }
        System.out.println("------------------------------------------------\n");
    }
}
