package edu.handong.leeejjju.pp01;
import java.util.ArrayList;
import java.util.Scanner;

//ICRUD interface의 구현체
public class WordCRUD implements ICRUD{

    private ArrayList<Word> list;
    private Scanner in;

    private int id;
    private int level = 1;
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
        //근데 id 안쓰는거같은데? 걍 인덱스로 하는 거 같은데 데이터상에 저장될 필요가 있으까..? 일단 냄겨ㅐ두긴 함
        if(list.isEmpty()) id = 1;
        else id = list.get(list.size()-1).getId()+1;
        while(true){
            System.out.print("난이도(1,2,3) & 새 단어 입력 : ");
            level = in.nextInt();
            word = in.nextLine();
            if(level > 0 && level < 4) break;
            else System.out.println("\n유효한 난이도를 입력해주세요 :(\n");
        }
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
    public int updateItem() {
        return 0;
    }


    /*
    => 삭제할 단어 검색 : er
    ------표시-------
    => 삭제할 번호 선택 : 1
    => 정말로 삭제하시겠습니까?(Y/N) Y
    단어가 삭제되었습니다.
     */
    @Override
    public int deleteItem() {
        ArrayList<Integer> s;
        int num = 0;
        String opt = null, one = null;

        System.out.printf("=> 삭제할단어 검색 : ");
        System.out.println("[delete] current one is " + one);
        one = in.nextLine();
        //검색 및 결과표시(함수사용)
        /*if(search(one) == -1){
            System.out.println("검색 결과가 없습니다.");
            return -1;
        }*/

        s = searchNew(one);
        if(s.isEmpty()){
            System.out.println("검색 결과가 없습니다.");
            return -1;
        }

        //삭제번호선택
        while (true){
            System.out.print("=> 삭제할 번호 선택(취소: -1) : ");
            num = in.nextInt();
            if(num > 0 && num <= list.size()) break;
            else if(num == -1) {
                System.out.println("취소되었습니다. \n");
                return -1;
            }
            System.out.println("유효한 번호를 선택해주세요 :(\n");
        }
        
        //재확인

        while (true){
            System.out.print("=> 정말로 삭제하시겠습니까?(Y/N) ");

            opt = in.nextLine();
            //System.out.println("[delete] current opt is " + opt);

            if(opt.equals("n") || opt.equals("N")) {
                System.out.println("취소되었습니다. \n");
                return -1;
            }else if(opt.equals("y") || opt.equals("Y")){
                break;
            }else{
                System.out.println("유효한 옵션을 선택해주세요 :(");
            }

        }

        //삭제작업
        System.out.println(s.get(num-1).toString());
        list.remove(s.get(num-1));
        System.out.println("단어가 삭제되었습니다.\n");
        return 0;

    }

    //option: d means delete, u means update, s means search
    //return: -1 means not found error. default : 0
    public int search(String str){

        boolean flag = false;
        System.out.println("[search()] current one is " + str);

        System.out.println("\n------------------------------------------------");
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getWord().contains(str)){
                System.out.println((i+1) + list.get(i).toString());
                flag = true;
            }
        }
        System.out.println("------------------------------------------------\n");

        if(flag) return 0;
        else return -1;
    }

    public ArrayList<Integer> searchNew(String str){

        //새로운 리스트를 만들어서... 결과값이 나오면 리스트에 그 인덱스를 담는거임. 시작할땐 리스트를 클리어해주고, 리스트의 크기로 empty를 판단하고.

        ArrayList<Integer> s = new ArrayList<>();

        //System.out.println("[search()] current one is " + str);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getWord().contains(str)){
                s.add(i);
            }
        }

        System.out.println("\n------------------------------------------------");
        for(int j = 0; j < s.size(); j++){
            System.out.println((j+1) + list.get(s.get(j)).toString());
        }
        System.out.println("------------------------------------------------\n");

        return s;
    }

    @Override
    public void selectOne(int id) {
        //TODO
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

    public void listSelectedLevel(){
        int lev;
        while(true){
            System.out.print("조회할 난이도를 선택하세요(1,2,3) : ");
            lev = in.nextInt();
            if(lev > 0 && lev < 4) break;
            else System.out.println("\n유효한 난이도를 입력해주세요 :(\n");
        }
        System.out.println("\n------------------------------------------------");
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getLevel() == lev) System.out.println((i+1) + list.get(i).toString());
        }
        System.out.println("------------------------------------------------\n");
    }







}
