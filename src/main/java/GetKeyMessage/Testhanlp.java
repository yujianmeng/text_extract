package GetKeyMessage;


import OperationBeExtract.ReadFile;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testhanlp {
    public static void main(String[] args) {
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……\n");
        //第一次运行会有文件找不到的错误但不影响运行，缓存完成后就不会再有了
       /* System.out.println("标准分词：");
        System.out.println(HanLP.segment("你好，欢迎使用HanLP！"));
        System.out.println("\n");

        List<Term> termList = NLPTokenizer.segment("中国科学院计算技术研究所的宗成庆教授正在教授自然语言处理课程");
        System.out.println("NLP分词：");
        System.out.println(termList);
        System.out.println("\n");*/

     /*   System.out.println("智能推荐：");
        getSegement();
        System.out.println("\n");*/

        System.out.println("关键字提取：");
        getMainIdea();
        System.out.println("\n");

       /* System.out.println("自动摘要：");
        getZhaiYao();
        System.out.println("\n");

        System.out.println("短语提取：");
        getDuanYu();
        System.out.println("\n");

        System.out.println("地名提取：");
        getDiMing();
        System.out.println("\n");

        System.out.println("人名提取：");
        getRenMing();
        System.out.println("\n");*/
    }

    /**
     * 智能推荐部分
     */
   /* public static void getSegement() {
        Suggester suggester = new Suggester();
        String[] titleArray = ("威廉王子发表演说 呼吁保护野生动物\n" + "《时代》年度人物最终入围名单出炉 普京马云入选\n" + "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n"
                + "日本保密法将正式生效 日媒指其损害国民知情权\n" + "英报告说空气污染带来“公共健康危机”").split("\\n");
        for (String title : titleArray) {
            suggester.addSentence(title);
        }
        System.out.println(suggester.suggest("发言", 1)); // 语义
        System.out.println(suggester.suggest("危机公共", 1)); // 字符
        System.out.println(suggester.suggest("mayun", 1)); // 拼音
    }*/

    /**
     * 关键字提取
     */
    public static void getMainIdea() {
//        String content = " 胡清波（12.18――12.23)12月18号从长沙出发 12月19号到现场下午：观察以前的动作记录与试验记录下面是上次投运时的总结报告4C励磁总结报告1：从这次成功投运过程发出，与4A相比，从人的感官上来年没有明显的改观。从甩负荷与甩线路来看有所改善。2：从建压过程来看，4C必须把KP升到100以上，才能稳定励磁建压。而从以往的试验过程来年KP的范围通常在60――120，而我们现在KP要调到100以上才能稳定建压，说明4C的调节参数范围有所缩小，至于，我们的PID参数是否最佳，由于没有专业的电力系统分析仪器，我们无法确定PID的范围。只有下一次与电科院在现场做励磁建模试验才能确定4C励磁的PID参数与系统的关系。 3：从励磁的调节感官上来看，与4A相比，做20%的阶跃，调节速度好像是有改进，但是苦于没有专业的仪器，不能在数据方面，专业的给出一个量的变化。上次投运回来，对第二点我一直想不通，为何KP要到100才能稳定建压，而在我们励磁通常的设定范围最大是60－120，而最大值设得越大，机组的容量就越大，通常是作为调压机组使用，而这是只有5000KW的机组，太小了，实在不应该，肯定是程序里有问题，而这个问题可能是导致无功突然降低的主要原因。到了现场，我又打电话咨询了调度，证明是配电所有无功补偿装置，并且一直有，当系统电压低或高时，就自动投入。仔细观察程序发现问题是启励的程序存在退它励时，给定电压与机端电压没有实现同步操作。把程序改好之后，等待晚上开机试验。晚上：开机试验程序，说明推断正确，空载阶跃我们把KP改为30可以稳定建压，把KP改为60以上，上阶人目示，没有超调，说明该机组最大的KP应该为60，改为30以下，发现建压不太稳定，所以我们把KP定为40.同时查出为PLC无功达到－3000Kvar延时0.1S跳，我们把1号机组并网，做8%以下的下阶跃，发现PLC不动，做10%的下阶跃PLC必动，同时可以看到PLC的动作记录。 从动作记录的波形来看，励磁调节的方向都是对的，发现1S之内给定只有加了1%,从理论上来说应该是加了5%，发现20ms控制一次的任务时间不对，程序里是100ms加一次，修改程序，重新做试验。发现做10%的下阶，依然动作，而且动作时是无功在快速增加过程中，可以断定是PLC延时时间过短，建议改为5S但为何以前的4A为何不动，而现在的4C又动呢，原因是以前4A的KP＝40，而4C的KP＝100，是它所导致的根本原因。12.20――12.22：观察机组发电情况。发现修改了程序之后，无功比以前稳定多了，所以我们认为问题已解决，下午回长备注：现场调试人员，无法利用经验确定阶跃量的大小决定多大的无功，没有在专业的人的指导下，切记不能并网做阶跃。尤其是大机组，否则由于深度进相会导致系统解列或损坏可控硅或灭磁电阻。";
        String fileContent = new ReadFile("D:\\华自\\textData\\事故描述.docx").readFileContent();
        List<String> keywordList = HanLP.extractKeyword(fileContent, 10);
        System.out.println(keywordList.toString());
        String words = "";
        for (String word:keywordList)
            words += word+"|";
        words = words.substring(0,words.length()-1);
        String content = new ReadFile("D:\\华自\\textData\\三都白梓桥优化运行2016.11.15-2016.11.25,李智.doc").readFileContent();
        String[] splitContent = content.split("，");
        Pattern p = Pattern.compile
                (words);
        List<String> result = new ArrayList<>();
        Matcher matcher;
        for (String s:splitContent){
            matcher = p.matcher(s);
            if (matcher.find())
                result.add(s);
        }
        for (String g:result)
            System.out.println(g);
    }

    /**
     * 自动摘要
     */
    public static void getZhaiYao() {
        String document = "算法可大致分为基本算法、数据结构的算法、数论算法、计算几何的算法、图的算法、动态规划以及数值分析、加密算法、排序算法、检索算法、随机化算法、并行算法、厄米变形模型、随机森林算法。\n"
                + "算法可以宽泛的分为三类，\n" + "一，有限的确定性算法，这类算法在有限的一段时间内终止。他们可能要花很长时间来执行指定的任务，但仍将在一定的时间内终止。这类算法得出的结果常取决于输入值。\n"
                + "二，有限的非确定算法，这类算法在有限的时间内终止。然而，对于一个（或一些）给定的数值，算法的结果并不是唯一的或确定的。\n"
                + "三，无限的算法，是那些由于没有定义终止定义条件，或定义的条件无法由输入的数据满足而不终止运行的算法。通常，无限算法的产生是由于未能确定的定义终止条件。";
        List<String> sentenceList = HanLP.extractSummary(document, 3);
        System.out.println(sentenceList);
    }

    /**
     * 短语提取
     */
    public static void getDuanYu() {
        String text = "算法工程师\n"
                + "算法（Algorithm）是一系列解决问题的清晰指令，也就是说，能够对一定规范的输入，在有限时间内获得所要求的输出。如果一个算法有缺陷，或不适合于某个问题，执行这个算法将不会解决这个问题。不同的算法可能用不同的时间、空间或效率来完成同样的任务。一个算法的优劣可以用空间复杂度与时间复杂度来衡量。算法工程师就是利用算法处理事物的人。\n"
                + "\n" + "1职位简介\n" + "算法工程师是一个非常高端的职位；\n" + "专业要求：计算机、电子、通信、数学等相关专业；\n"
                + "学历要求：本科及其以上的学历，大多数是硕士学历及其以上；\n" + "语言要求：英语要求是熟练，基本上能阅读国外专业书刊；\n"
                + "必须掌握计算机相关知识，熟练使用仿真工具MATLAB等，必须会一门编程语言。\n" + "\n" + "2研究方向\n"
                + "视频算法工程师、图像处理算法工程师、音频算法工程师 通信基带算法工程师\n" + "\n" + "3目前国内外状况\n"
                + "目前国内从事算法研究的工程师不少，但是高级算法工程师却很少，是一个非常紧缺的专业工程师。算法工程师根据研究领域来分主要有音频/视频算法处理、图像技术方面的二维信息算法处理和通信物理层、雷达信号处理、生物医学信号处理等领域的一维信息算法处理。\n"
                + "在计算机音视频和图形图像技术等二维信息算法处理方面目前比较先进的视频处理算法：机器视觉成为此类算法研究的核心；另外还有2D转3D算法(2D-to-3D conversion)，去隔行算法(de-interlacing)，运动估计运动补偿算法(Motion estimation/Motion Compensation)，去噪算法(Noise Reduction)，缩放算法(scaling)，锐化处理算法(Sharpness)，超分辨率算法(Super Resolution),手势识别(gesture recognition),人脸识别(face recognition)。\n"
                + "在通信物理层等一维信息领域目前常用的算法：无线领域的RRM、RTT，传送领域的调制解调、信道均衡、信号检测、网络优化、信号分解等。\n" + "另外数据挖掘、互联网搜索算法也成为当今的热门方向。\n"
                + "算法工程师逐渐往人工智能方向发展。";
        List<String> phraseList = HanLP.extractPhrase(text, 10);
        System.out.println(phraseList);
    }


    public static void getDiMing() {
        String[] testCase = new String[]{
                "武胜县新学乡政府大楼门前锣鼓喧天",
                "蓝翔给宁夏固原市彭阳县红河镇黑牛沟村捐赠了挖掘机",
        };
        Segment segment = HanLP.newSegment().enablePlaceRecognize(true);
        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }
    }

    public static void getRenMing() {
        String[] testCase = new String[]{
                "签约仪式前，秦光荣、李纪恒、仇和等一同会见了参加签约的企业家。",
                "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四",
                "张浩和胡健康复员回家了",
                "王总和小丽结婚了",
                "编剧邵钧林和稽道青说",
                "这里有关天培的有关事迹",
                "龚学平等领导,邓颖超生前",
        };
        Segment segment = HanLP.newSegment().enableNameRecognize(true);
        for (String sentence : testCase)
        {
            List<Term> termList = segment.seg(sentence);
            System.out.println(termList);
        }
    }
}
