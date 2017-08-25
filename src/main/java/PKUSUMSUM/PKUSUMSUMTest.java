package PKUSUMSUM;

/**
 * Created by yujian on 2017/8/1.
 */
public class PKUSUMSUMTest {

    public static void main(String[] args) throws Exception{
        Run run = new Run();
//        java -jar PKUSUMSUM.jar –T 1 –input ./article.txt –output ./summay.txt –L 1 –n 100 –m 2 –stop n
        String[] parameters = {
                        "-T","1",
                        "-input", "D:\\华自\\test\\data.txt",
                        "-output","D:\\华自\\test\\summary.txt",
                        "-L","1",
                        "-n","100",
                        "-m","2",
                        "-stop","n"};
        run.getSummary(parameters);
    }
}
