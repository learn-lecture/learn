package hellojpa;

public class DdlMain {

    public static void main(String[] args) {
        TransactionManager.executeInTransaction(em -> {

        });
    }

}
