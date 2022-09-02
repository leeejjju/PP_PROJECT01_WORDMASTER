package edu.handong.leeejjju.pp01;

//데이터 클래스. 보통 데이터클래스를 제일 먼저 만들어준다구 합니당
public class Word {

    private int id;
    private int level;
    private String word;
    private String meaning;
    private String levelStar[] = {"*   ", "**  ", "*** "};


    public Word(){}
    public Word(int id, int level, String word, String meaning) {
        this.id = id;
        this.level = level;
        this.word = word;
        this.meaning = meaning;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString(){
        String formattedWord =
                String.format(". %s\t%25s   %s",
                        levelStar[level-1],
                        word,
                        meaning);
        return formattedWord;
    }



}
