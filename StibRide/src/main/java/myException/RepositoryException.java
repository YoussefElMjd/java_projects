package myException;

public class RepositoryException extends Exception {

    public RepositoryException(Exception ex){
        super(ex);
    }
    public RepositoryException(String msg){
        super(msg);
    }
    public RepositoryException(){
        super();
    }

}
