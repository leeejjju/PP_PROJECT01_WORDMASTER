package edu.handong.leeejjju.pp01;
import java.util.Scanner;

//wordCRUD를 사용한 실제 관리
public class WordManager {
    private Scanner in = new Scanner(System.in); // 입력을 위한 Scanner 객체
    private int CMD = 1;
    private WordCRUD myWordCRUD;

    public WordManager() {
        myWordCRUD = new WordCRUD();
    }

    public void start(){
        System.out.println("\n**** 영단어 마스터 ****");
        while(CMD != 0){
            CMD = selectMenu();
            switch (CMD){
                case 0 :
                    System.out.println("\n행복한 하루 보내세요 XD");
                    break;
                case 1:
                    //전체보기
                    myWordCRUD.listAll();
                    break;
                case 2:
                    //수준별보기
                    break;
                case 3:
                    //검색
                    break;
                case 4:
                    //추가
                    myWordCRUD.addWord();
                    break;
                case 5:
                    //수정
                    //list.set(int index, Object)
                    break;
                case 6:
                    //삭제
                    //list.remove(int index-1)
                    break;
                case 7:
                    //파일저장
                    break;
                default:
                    System.out.println("\n유효한 숫자를 입력해주세요 :(\n");
            }
        }
    }

    //메뉴 보여주기
    public int selectMenu(){

        System.out.println("*********************\n"
                + "1. 모든 단어 보기\n"
                + "2. 수준별 단어 보기\n"
                + "3. 단어 검색\n"
                + "4. 단어 추가\n"
                + "5. 단어 수정\n"
                + "6. 단어 삭제\n"
                + "7. 파일 저장\n"
                + "0. 나가기\n"
                + "*********************\n"
                + "=> 원하는 메뉴는? ");
        return in.nextInt();

    }




}
