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


    //constructor
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

    /*
    => 수정할 단어 검색 : er
    ------표시-------
    => 수정할 번호 선택 : 1
    => 뜻 입력 : 옮기다, 이동하다, 이동, 전송 
    단어가 수정되었습니다.
     */
    @Override
    public int updateItem() {
        ArrayList<Integer> s; //검색결과로 나온 친구들의 인덱스를 담을 리스트에여
        int num = 0;
        String foo;

        /*
        nextInt() 메소드 다음에 nextLine() 메소드를 실행하려고 할때 nextLine()메소드가 그냥 넘어가버리는 오류가 생겨난다.
        이 이유는 nextInt()메소드를 실행 할 때 20을 콘솔에 입력하고 엔터를 누를때 20을 리턴시켰지만 Enter값은 그대로 남아있다.
        nextLine() 메소드는 Enter값을 기준으로 메소드를 종료시키기 때문에 nextLine()메소드가 실행될 때 남아있는 Enter값을 그대로 읽어
        바로 종료된 것이다. 그래서 첫번째 문자열입력: 이 넘어가고 두번째 정수입력: 이 출력된 것이다.
        만약 정수를 입력하고 그다음 문자를 입력하려고 할 때 next() 메소드를 사용하여야 한다.
        아니면 위에 nextLine()메소드를 한번더 써줘서 enter값을 없애줘야한다.
        출처: https://deftkang.tistory.com/55 [deftkang의 IT 블로그:티스토리]
         */
        System.out.printf("=> 수정할 단어 검색 : ");
        foo = in.next();
        //System.out.println("[delete] current foo is " + foo);
        //검색 및 결과표시(함수사용)
        s = search(foo);
        if (s.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return -1;
        }

        //삭제번호선택
        while (true) {
            System.out.print("=> 수정할 번호 선택(취소: -1) : ");
            num = in.nextInt(); //여기서 num에 들어갈친구는 검색결과로 만들어진 리스트 기준임을 잊지말ㄹㄴㅇㄹㄴㄱ

            if (num == -1) {
                System.out.println("취소되었습니다. \n");
                return -1;
            } else if (num <= 0 || num > s.size()) {
                System.out.println("유효한 번호를 선택해주세요 :(\n");
            } else break;

        }

        System.out.print("=> 뜻 입력 : ");
        in.nextLine();//거시기방지용
        foo = in.nextLine();
        int updateIntex = s.get(num - 1);
        list.get(updateIntex).setMeaning(foo);
        System.out.println("단어가 수정되었습니다.\n");
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
        ArrayList<Integer> s; //검색결과로 나온 친구들의 인덱스를 담을 리스트에여
        int num = 0;
        String foo;

        /*
        nextInt() 메소드 다음에 nextLine() 메소드를 실행하려고 할때 nextLine()메소드가 그냥 넘어가버리는 오류가 생겨난다.
        이 이유는 nextInt()메소드를 실행 할 때 20을 콘솔에 입력하고 엔터를 누를때 20을 리턴시켰지만 Enter값은 그대로 남아있다.
        nextLine() 메소드는 Enter값을 기준으로 메소드를 종료시키기 때문에 nextLine()메소드가 실행될 때 남아있는 Enter값을 그대로 읽어
        바로 종료된 것이다. 그래서 첫번째 문자열입력: 이 넘어가고 두번째 정수입력: 이 출력된 것이다.
        만약 정수를 입력하고 그다음 문자를 입력하려고 할 때 next() 메소드를 사용하여야 한다.
        아니면 위에 nextLine()메소드를 한번더 써줘서 enter값을 없애줘야한다.
        출처: https://deftkang.tistory.com/55 [deftkang의 IT 블로그:티스토리]
         */
        System.out.printf("=> 삭제할 단어 검색 : ");
        foo = in.next();
        //System.out.println("[delete] current foo is " + foo);
        //검색 및 결과표시(함수사용)
        s = search(foo);
        if (s.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return -1;
        }

        //삭제번호선택
        while (true) {
            System.out.print("=> 삭제할 번호 선택(취소: -1) : ");
            num = in.nextInt(); //여기서 num에 들어갈친구는 검색결과로 만들어진 리스트 기준임을 잊지말ㄹㄴㅇㄹㄴㄱ

            if (num == -1) {
                System.out.println("취소되었습니다. \n");
                return -1;
            } else if (num <= 0 || num > s.size()) {
                System.out.println("유효한 번호를 선택해주세요 :(\n");
            } else break;

        }

        //재확인
        while (true) {
            System.out.print("=> 정말로 삭제하시겠습니까?(Y/N) ");
            foo = in.next();
            //System.out.println("[delete] current foo is " + foo);

            if (foo.equals("n") || foo.equals("N")) {
                System.out.println("취소되었습니다. \n");
                return -1;
            } else if (foo.equals("y") || foo.equals("Y")) {
                break;
            } else System.out.println("유효한 옵션을 선택해주세요 :(");

        }

        //삭제작업
        int removeIndex = s.get(num - 1);
        list.remove(removeIndex);
        System.out.println("단어가 삭제되었습니다.\n");
        return 0;


    }

    public ArrayList<Integer> search(String str){

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
