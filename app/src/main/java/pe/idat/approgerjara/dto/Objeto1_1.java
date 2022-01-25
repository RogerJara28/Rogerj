package pe.idat.approgerjara.dto;

import java.util.List;

public class Objeto1_1 {
    private int count;
    private String next;
    private String previous;
    private List<Objeto1> results;


    public Objeto1_1(int count, String next, String previous, List<Objeto1> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Objeto1> getResults() {
        return results;
    }

    public void setResults(List<Objeto1> results) {
        this.results = results;
    }
}
