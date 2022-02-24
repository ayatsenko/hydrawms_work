package com.idltd.hydramob.Sheduler;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlType(name="question")
public class Question {
    private int id;
    private String questionname;
    private List<Answer> answers;
    public Question() {}
    public Question(int id, String questionname, List<Answer> answers) {
        super();
        this.id = id;
        this.questionname = questionname;
        this.answers = answers;
    }
    @XmlAttribute
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @XmlElement(name="question")
    public String getQuestionname() {
        return questionname;
    }
    public void setQuestionname(String questionname) {
        this.questionname = questionname;
    }
    @XmlElement
    public List<Answer> getAnswers() {
        return answers;
    }
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}