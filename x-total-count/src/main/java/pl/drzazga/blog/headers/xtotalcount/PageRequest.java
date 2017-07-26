package pl.drzazga.blog.headers.xtotalcount;

public class PageRequest {

    private int number;

    private int size;
    
    public PageRequest(int number, int size) {
        this.number = number;
        this.size = size;
    }

    int getSize() {
        return size;
    }
    
    int getOffset() {
        return (number - 1) * size;
    }
}
