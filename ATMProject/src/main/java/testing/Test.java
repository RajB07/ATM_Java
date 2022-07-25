package testing;

public class Test {
    private String uid = "000001";
    public String getNewUserUUID() {
        //initialize
        String uuid = uid;
        uid = String.valueOf(Integer.parseInt((uid)+1));
        return uuid;
    }

    public static void main(String[] args) {
      Test t = new Test();
//        String t2 =   t1.getNewUserUUID();
//        System.out.println(t2);

        // Test1 t1 = new Test1(t);


    }

}
